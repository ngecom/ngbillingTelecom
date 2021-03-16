package com.ngbilling.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ngbilling.core.server.persistence.dao.user.UserDAO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.RoleDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;
import com.ngbilling.core.server.util.ServerConstants;
import com.ngbilling.core.server.persistence.dao.user.CompanyDAO;
import com.ngbilling.core.server.persistence.dao.user.RoleDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.ngbilling.core.common.exception.MessageResponse;

import com.ngbilling.core.payload.request.user.UserWS;
import com.ngbilling.core.security.JwtUtils;

@RestController
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserDAO userDAO;

	@Autowired
	RoleDAO roleDAO;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	@Autowired
	CompanyDAO companyDAO;
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserWS userWS){
		if (userDAO.existsByUserName(userWS.getUserName())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userDAO.findEmail(userWS.getContact().getEmail()).get()>0) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		if(companyDAO.findByDescription(userWS.getContact().getOrganizationName())!=null) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: company.already.exists"));
		}
		
		

		return ResponseEntity.ok(new MessageResponse(userWS.getUserName()+" User registered successfully!"));
	}
	
	private UserDTO createUser(LanguageDTO language, CurrencyDTO currency, CompanyDTO company) {
		UserDTO userDTO = new UserDTO();
        return userDTO;
    }
	
	/*
	 * This is applicable for EntityId
	 */
	private void createDefaultRoles(LanguageDTO language, CurrencyDTO currency, CompanyDTO company) {

		int[] defaultRoleList = { ServerConstants.TYPE_ROOT, ServerConstants.TYPE_CLERK, ServerConstants.TYPE_CUSTOMER, ServerConstants.TYPE_PARTNER };


		for (int defaultRole : defaultRoleList)  {

			List roleList = roleDAO.findRoleTypeIdAndCompanyId(defaultRole, company.getId());
			if (roleList!=null&&roleList.size()>0) {
				return;
			}
			RoleDTO newRole = new RoleDTO();
			newRole.setCompany(company);
			newRole.setRoleTypeId(defaultRole);
			newRole.setDescription(newRole.getDescription(language.getId()),language.getId());
			newRole.setDescription("title", language.getId(), newRole.getTitle(language.getId()));
			roleDAO.save(newRole);
		}
	}

}
