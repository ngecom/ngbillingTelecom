package com.ngbilling.core.server.service.user;

import java.util.Locale;

import com.ngbilling.core.payload.request.user.MainSubscriptionWS;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.MainSubscriptionDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;

public interface UserService {

	public Locale getLocale(UserDTO user);
	public Integer getMainRole(UserDTO user);
	public MainSubscriptionDTO convertMainSubscriptionFromWS (MainSubscriptionWS mainSubscriptionWS,Integer entityId);
	public CompanyDTO getEntity (Integer userId);
}
