package com.ngbilling.core.server.service.user.impl;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngbilling.core.common.exception.SessionInternalError;
import com.ngbilling.core.common.util.CommonConstants;
import com.ngbilling.core.payload.request.user.MainSubscriptionWS;
import com.ngbilling.core.payload.request.user.UserWS;
import com.ngbilling.core.server.notification.NotificationNotFoundException;
import com.ngbilling.core.server.persistence.dao.order.OrderPeriodDAO;
import com.ngbilling.core.server.persistence.dao.user.CompanyDAO;
import com.ngbilling.core.server.persistence.dao.user.ContactDAO;
import com.ngbilling.core.server.persistence.dao.user.ContactMapDAO;
import com.ngbilling.core.server.persistence.dao.user.RoleDAO;
import com.ngbilling.core.server.persistence.dao.user.UserDAO;
import com.ngbilling.core.server.persistence.dao.user.UserStatusDAO;
import com.ngbilling.core.server.persistence.dao.user.UserSubscriptionStatusDAO;
import com.ngbilling.core.server.persistence.dto.contact.ContactDTO;
import com.ngbilling.core.server.persistence.dto.contact.ContactMapDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.MainSubscriptionDTO;
import com.ngbilling.core.server.persistence.dto.user.RoleDTO;
import com.ngbilling.core.server.persistence.dto.user.SubscriberStatusDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.user.UserStatusDTO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;
import com.ngbilling.core.server.service.user.UserService;
import com.ngbilling.core.server.service.util.UtilService;
import com.ngbilling.core.server.util.ServerConstants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private OrderPeriodDAO orderPeriodDAO;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private ContactMapDAO contactMapDAO;
	
	@Autowired
	private UserStatusDAO userStatusDAO;
	
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private UserSubscriptionStatusDAO userSubscriptionStatusDAO;
	
	@Autowired
	private UtilService utilService;
	
	@Override
	public Locale getLocale(UserDTO user) {
		 String languageCode = user.getLanguage().getCode();
         ContactDTO contact = contactDAO.findContact(ServerConstants.TABLE_BASE_USER,user.getId());
         String countryCode = null;
         if (contact != null)
            countryCode = contact.getCountryCode();
	        return countryCode != null ? new Locale(languageCode, countryCode) : new Locale(languageCode);
	}

	@Override
	public Integer getMainRole(UserDTO user) {
		//Get UserRoles.RoleIds get min roleType
		 return null;
	}

	@Override
	public MainSubscriptionDTO convertMainSubscriptionFromWS(MainSubscriptionWS mainSubscriptionWS, Integer entityId) {
		 if (mainSubscriptionWS == null) {
			 return new MainSubscriptionDTO(orderPeriodDAO.findOrderPeriod(	entityId, 1, 1), 1);
	        }

	        MainSubscriptionDTO mainSub = new MainSubscriptionDTO();
	        mainSub.setSubsriptionPeriodFromPeriodId(mainSubscriptionWS.getPeriodId());
	        mainSub.setNextInvoiceDayOfPeriod(mainSubscriptionWS.getNextInvoiceDayOfPeriod());
	        return mainSub;
	}

	@Override
	public CompanyDTO getEntity(Integer userId) {
		return userDAO.getEntity(userId);
	}

	@Override
	public CompanyDTO createCompany(UserWS userWS) {
		CompanyDTO company = new CompanyDTO();
		company.setDescription(StringUtils.left(userWS.getContact().getOrganizationName(), 100));
		company.setCreateDatetime(new Date());
		LanguageDTO language = utilService.findByLanguageCode(userWS.getLanguageCode());
		CurrencyDTO currency = utilService.findByCurrencyCode(userWS.getCurrencyCode());
		company.setLanguage(language);
		company.setCurrency(currency);
		company.setDeleted(0);
		return companyDAO.save(company);
	}

	@Override
	public ContactDTO createContact(ContactDTO contactDTO,CompanyDTO companyDTO,String tableName) {
		contactDTO = contactDAO.save(contactDTO);
		ContactMapDTO contactMapDTO = new ContactMapDTO();
		contactMapDTO.setJbillingTable(utilService.findByName(tableName));
		contactMapDTO.setContact(contactDTO);
		contactMapDTO.setForeignId(companyDTO.getId());
		contactMapDAO.save(contactMapDTO);
		return contactDTO;
	}

	@Override
	public CompanyDTO findByDescription(String companyName) {
		return companyDAO.findByDescription(companyName);
	}

	@Override
	public Boolean existsByUserName(String username) {
		return userDAO.existsByUserName(username);
	}

	@Override
	public Integer findEmail(String email) {
		return userDAO.findEmail(email).get();
	}

	@Override
	public UserDTO createAdminUser(UserDTO userDTO ) {
		userDTO.setCurrency(userDTO.getCompany().getCurrency());
		userDTO.setLanguage(userDTO.getCompany().getLanguage());
		userDTO.setDeleted(0); 
		userDTO.setUserStatus(userStatusDAO.findById(CommonConstants.STATUS_ACTIVE).get());
		userDTO.setSubscriberStatus(userSubscriptionStatusDAO.findById(CommonConstants.SUBSCRIBER_ACTIVE).get());
		userDTO.setCreateDatetime(new Date());
		return userDTO;
	}

	@Override
	public UserStatusDTO findByStatus(Integer statusID) {
		return userStatusDAO.findById(statusID).get();
	}

	@Override
	public SubscriberStatusDTO findBySubscriptionStatus(Integer statusID) {
		return userSubscriptionStatusDAO.findById(statusID).get();
	}

	@Override
	public List<RoleDTO> findRoleTypeIdAndCompanyId(Integer roleTypeId, Integer companyId) {
		return roleDAO.findRoleTypeIdAndCompanyId(roleTypeId, companyId);
	}

	@Override
	public RoleDTO createRole(Integer roleTypeId, CompanyDTO companyDTO) {
		RoleDTO newRole = new RoleDTO();
		newRole.setCompany(companyDTO);
		newRole.setRoleTypeId(roleTypeId);
		newRole.setDescription(newRole.getDescription(companyDTO.getLanguage().getId()),companyDTO.getLanguage().getId());
		newRole.setDescription("title", companyDTO.getLanguage().getId(), newRole.getTitle(companyDTO.getLanguage().getId()));
		roleDAO.save(newRole);
		return newRole;
	}
	
	@Override
	public void sendCredentials(Integer entityId, Integer userId, Integer languageId, String link)
			throws SessionInternalError, NotificationNotFoundException {
		// TODO Auto-generated method stub
		
	}

}
