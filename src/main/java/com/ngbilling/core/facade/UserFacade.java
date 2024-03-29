package com.ngbilling.core.facade;

import com.ngbilling.core.payload.request.user.CompanyUserDetails;
import com.ngbilling.core.payload.request.user.UserWS;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import org.springframework.security.core.Authentication;

public interface UserFacade {
    public UserDTO createCompany(UserWS userWS);
    CompanyUserDetails getAuthentication();
}
