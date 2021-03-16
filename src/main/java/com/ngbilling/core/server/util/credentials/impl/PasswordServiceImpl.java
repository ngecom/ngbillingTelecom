package com.ngbilling.core.server.util.credentials.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ngbilling.core.common.exception.SessionInternalError;
import com.ngbilling.core.common.util.FormatLogger;
import com.ngbilling.core.common.util.Util;
import com.ngbilling.core.server.notification.NotificationNotFoundException;
import com.ngbilling.core.server.persistence.dao.user.ResetPasswordCodeDAO;
import com.ngbilling.core.server.persistence.dto.user.ResetPasswordCodeDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.service.user.UserService;
import com.ngbilling.core.server.util.credentials.PasswordService;

@Service
public class PasswordServiceImpl implements PasswordService{

    private final static FormatLogger LOG = new FormatLogger(PasswordServiceImpl.class);
    
	@Autowired
	private ResetPasswordCodeDAO resetPasswordCodeDAO;
	
	@Autowired   
	private UserService userService;
	
	@Override
	public void createPassword(UserDTO user) {
		// TODO Auto-generated method stub
		 ResetPasswordCodeDTO resetCodeDTO = new ResetPasswordCodeDTO();
		 resetCodeDTO.setUser(user);
		 resetCodeDTO.setDateCreated(new Date());
		 resetCodeDTO.setToken(RandomStringUtils.random(32, true, true));
	        resetPasswordCodeDAO.save(resetCodeDTO);
	        
	        try {
	            userService.sendCredentials(user.getCompany().getId(), user.getId(), 1,
	                    generateLink(resetCodeDTO.getToken()));
	        } catch (SessionInternalError e) {
	            LOG.error(e.getMessage(), e);
	            throw new SessionInternalError("Exception while sending notification : " + e.getMessage());
	        } catch (NotificationNotFoundException e) {
	            LOG.error(e.getMessage(), e);
	            throw new SessionInternalError("createCredentials.notification.not.found");
	        }
  
	        
		
	}

	@Override
	public void resetPassword(UserDTO user) {
		// TODO Auto-generated method stub
		
	}

	private String generateLink(String token) {
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> params = new HashMap<String, String>();
        params.put("token", token);
        map.put("controller", "resetPassword");
        map.put("action", "changePassword");
        map.put("params", params);
        //String link = Util.getSysProp("url") + new ApplicationTagLib().createLink(map);

        return null;
    }
	
}
