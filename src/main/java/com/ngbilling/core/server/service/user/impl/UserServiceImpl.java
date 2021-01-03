package com.ngbilling.core.server.service.user.impl;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngbilling.core.payload.request.user.MainSubscriptionWS;
import com.ngbilling.core.server.persistence.dao.order.OrderPeriodDAO;
import com.ngbilling.core.server.persistence.dao.user.ContactDAO;
import com.ngbilling.core.server.persistence.dao.user.UserDAO;
import com.ngbilling.core.server.persistence.dto.contact.ContactDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.MainSubscriptionDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.service.user.UserService;
import com.ngbilling.core.server.util.ServerConstants;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ContactDAO contactDAO;
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private OrderPeriodDAO orderPeriodDAO;
	
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

}
