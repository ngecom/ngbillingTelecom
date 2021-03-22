package com.ngbilling.core.server.util.credentials;

import com.ngbilling.core.server.persistence.dto.user.UserDTO;

public interface PasswordService {

	    public void createPassword(UserDTO user);
	    /**
	     * Method for reset user password for a given user
	     *
	     * @param user the user
	     */
	    public void resetPassword(UserDTO user);

	
}
