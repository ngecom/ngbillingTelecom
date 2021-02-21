/*
 jBilling - The Enterprise Open Source Billing System
 Copyright (C) 2003-2011 Enterprise jBilling Software Ltd. and Emiliano Conde

 This file is part of jbilling.

 jbilling is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 jbilling is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with jbilling.  If not, see <http://www.gnu.org/licenses/>.

 This source was modified by Web Data Technologies LLP (www.webdatatechnologies.in) since 15 Nov 2015.
 You may download the latest source from webdataconsulting.github.io.

 */

package com.ngbilling.core.server.service.user.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ngbilling.core.payload.request.user.CompanyUserDetails;
import com.ngbilling.core.server.persistence.dao.user.UserDAO;
import com.ngbilling.core.server.persistence.dto.user.RoleDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.service.user.UserService;


/**
 * An implementation of the GrailsUserDetailsService for use with the default DaoAuthenticationProvider. This
 * class fetches a user from the database and builds a list of granted authorities from the users assigned
 * permissions and roles.
 *
 * This must be used with the {@link CompanyUserAuthenticationFilter} to provide the company ID as part
 * of the username to load.
 *
 * @author Brian Cowdery
 * @since 04-10-2010
 */
@Service
public class CompanyUserDetailsService implements UserDetailsService {

	@Autowired
	UserDAO userDAO;
	
	@Autowired
	UserService userService;
	
    public UserDetails loadUserByUsername(String s, boolean loadRoles)
            throws UsernameNotFoundException, DataAccessException {
        return loadUserByUsername(s);
    }

    /**
     * Loads the user account and all permissions/roles for the given user name. This method does not perform
     * authentication, only retrieves the {@link UserDetails} so that authentication can proceed.
     *
     * @param s username (principal) to retrieve
     * @return found user details
     * @throws AuthenticationException
     * @throws DataAccessException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {


    	UserDTO user = userDAO.findByUserName(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        List<Integer> roleIds = new LinkedList<Integer>();
        for (RoleDTO role : user.getRoles()) {
            role.initializeAuthority();            
            authorities.add(role);
            roleIds.add(role.getRoleTypeId());
        }
        
        
        // return user details for the retrieved account
        return new CompanyUserDetails(username,user.getPassword(), user.isEnabled(),
                                      !user.isAccountExpired(), !user.isPasswordExpired(), !user.isAccountLocked(),
                                      authorities,
                                      user, userService.getLocale(user), user.getId(), selectMainRole(roleIds),
                                      user.getEntity().getId(), user.getCurrency().getId(), user.getLanguage().getId());
    }
    
    private Integer selectMainRole(Collection<Integer> allRoleIds){
        Integer result = null;
        for (Iterator<Integer> roleIds = allRoleIds.iterator(); roleIds.hasNext();){
            Integer nextId = (Integer)roleIds.next();
            if (result == null || nextId.compareTo(result) < 0) {
                result = nextId;
            }
        }
        return result;
    }

}
