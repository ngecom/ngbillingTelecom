package com.ngbilling.core.server.service.user;

import java.util.List;
import java.util.Locale;

import com.ngbilling.core.common.exception.SessionInternalError;
import com.ngbilling.core.payload.request.user.MainSubscriptionWS;
import com.ngbilling.core.payload.request.user.UserWS;
import com.ngbilling.core.server.notification.NotificationNotFoundException;
import com.ngbilling.core.server.persistence.dto.contact.ContactDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.MainSubscriptionDTO;
import com.ngbilling.core.server.persistence.dto.user.RoleDTO;
import com.ngbilling.core.server.persistence.dto.user.SubscriberStatusDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.user.UserStatusDTO;

public interface UserService {

	public Locale getLocale(UserDTO user);
	public Integer getMainRole(UserDTO user);
	public MainSubscriptionDTO convertMainSubscriptionFromWS (MainSubscriptionWS mainSubscriptionWS,Integer entityId);
	public CompanyDTO getEntity (Integer userId);
	public CompanyDTO createCompany(UserWS userWS);
	public UserDTO createAdminUser(UserDTO userDTO);
	public CompanyDTO findByDescription(String companyName);
	public ContactDTO createContact(ContactDTO contactDTO,CompanyDTO companyDTO,String tableName);
	public Boolean existsByUserName(String username);
	public Integer findEmail(String email);
	public UserStatusDTO findByStatus(Integer statusID);
	public SubscriberStatusDTO findBySubscriptionStatus(Integer statusID);
	public List<RoleDTO> findRoleTypeIdAndCompanyId(Integer roleTypeId, Integer companyId);
	public RoleDTO createRole(Integer roleTypeId,CompanyDTO companyDTO);
	void sendCredentials(Integer entityId, Integer userId, Integer languageId, String link)
			throws SessionInternalError, NotificationNotFoundException;
}
