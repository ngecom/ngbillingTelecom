package com.ngbilling.core.server.service.user.impl;

import com.ngbilling.core.common.util.CommonConstants;
import com.ngbilling.core.payload.request.user.MainSubscriptionWS;
import com.ngbilling.core.server.persistence.dao.order.OrderPeriodDAO;
import com.ngbilling.core.server.persistence.dao.user.*;
import com.ngbilling.core.server.persistence.dto.contact.ContactDTO;
import com.ngbilling.core.server.persistence.dto.contact.ContactMapDTO;
import com.ngbilling.core.server.persistence.dto.user.*;
import com.ngbilling.core.server.service.item.ProductService;
import com.ngbilling.core.server.service.user.UserService;
import com.ngbilling.core.server.service.util.UtilService;
import com.ngbilling.core.server.util.ServerConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Locale;

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

    @Autowired
    ProductService productService;

    @Override
    public Locale getLocale(UserDTO user) {
        String languageCode = user.getLanguage().getCode();
        ContactDTO contact = contactDAO.findContact(ServerConstants.TABLE_BASE_USER, user.getId());
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
            return new MainSubscriptionDTO(orderPeriodDAO.findOrderPeriod(entityId, 1, 1), 1);
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
    @Transactional
    public UserDTO createCompany(UserDTO userDTO) {
        CompanyDTO company = new CompanyDTO();
        company.setDescription(StringUtils.left(userDTO.getContact().getOrganizationName(), 100));
        company.setCreateDatetime(new Date());
        company.setLanguage(userDTO.getLanguage());
        company.setCurrency(userDTO.getCurrency());
        company.setDeleted(0);
        userDTO.setCompany(companyDAO.save(company));
        ContactDTO adminContact  = userDTO.getContact();
        userDTO.setContact(null);
        createContact(adminContact, ServerConstants.TABLE_ENTITY,userDTO.getCompany().getId());
        createRole(ServerConstants.TYPE_ROOT, userDTO); createRole(ServerConstants.TYPE_CLERK, userDTO); createRole(ServerConstants.TYPE_CUSTOMER, userDTO);
        userDTO = createAdminUser(userDTO);
        createContact(adminContact, ServerConstants.TABLE_BASE_USER,userDTO.getId());
        utilService.initEntityDefault(userDTO,getLocale(userDTO));
        productService.createInternalTypeCategory(company);
        return userDTO;
    }

    @Override
    @Transactional
    public ContactDTO createContact(ContactDTO contactUserDTO, String tableName,Integer foreignId) {
        ContactDTO contactDTO = contactDAO.save(contactUserDTO);
        ContactMapDTO contactMapDTO = new ContactMapDTO();
        contactMapDTO.setJbillingTable(utilService.findByName(tableName));
        contactMapDTO.setContact(contactDTO);
        contactMapDTO.setForeignId(foreignId);
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
    @Transactional
    public UserDTO createAdminUser(UserDTO userDTO) {
        userDTO.setCurrency(userDTO.getCompany().getCurrency());
        userDTO.setLanguage(userDTO.getCompany().getLanguage());
        userDTO.setDeleted(0);
        userDTO.setUserStatus(userStatusDAO.findById(CommonConstants.STATUS_ACTIVE).get());
        userDTO.setSubscriberStatus(userSubscriptionStatusDAO.findByStatusValue(CommonConstants.SUBSCRIBER_ACTIVE));
        userDTO.setCreateDatetime(new Date());
        List<RoleDTO> roleList = findRoleTypeIdAndCompanyId(ServerConstants.TYPE_ROOT, userDTO.getCompany().getId());
        userDTO.getRoles().add(roleList.get(0));
        userDTO = userDAO.save(userDTO);
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
    public List<RoleDTO> findByRoleTypeId(Integer roleTypeId) {
        return roleDAO.findByRoleTypeId(roleTypeId);
    }

    @Override
    @Transactional
    public RoleDTO createRole(Integer roleTypeId, UserDTO userDTO) {
        List<RoleDTO> roleDTOList = findByRoleTypeId(roleTypeId);
        RoleDTO existingRole = roleDTOList.get(0);
        RoleDTO newRole = new RoleDTO();
        newRole.setCompany(userDTO.getCompany());
        newRole.setRoleTypeId(roleTypeId);
        roleDAO.save(newRole);
        String description  = utilService.getDescription(ServerConstants.TABLE_ROLE, existingRole.getId(), "description",userDTO.getCompany().getLanguageId());
        String title =  utilService.getDescription(ServerConstants.TABLE_ROLE, existingRole.getId(), "title",userDTO.getCompany().getLanguageId());
        utilService.setDescription(ServerConstants.TABLE_ROLE,newRole.getId(),"description",userDTO.getCompany().getLanguageId(), description);
        utilService.setDescription(ServerConstants.TABLE_ROLE,newRole.getId(),"title",userDTO.getCompany().getLanguageId(), title);
        return newRole;
    }


}
