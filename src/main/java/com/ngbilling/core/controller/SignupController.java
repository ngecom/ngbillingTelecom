package com.ngbilling.core.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ngbilling.core.server.persistence.dao.user.UserDAO;
import com.ngbilling.core.server.persistence.dao.util.CurrencyDAO;
import com.ngbilling.core.server.persistence.dao.util.JbillingTableDAO;
import com.ngbilling.core.server.persistence.dao.util.LanguageDAO;
import com.ngbilling.core.server.persistence.dto.contact.ContactDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.RoleDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;
import com.ngbilling.core.server.service.user.UserService;
import com.ngbilling.core.server.service.util.UtilService;
import com.ngbilling.core.server.util.ServerConstants;
import com.ngbilling.core.server.persistence.dao.user.CompanyDAO;
import com.ngbilling.core.server.persistence.dao.user.RoleDAO;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.ngbilling.core.common.exception.MessageResponse;

import com.ngbilling.core.payload.request.user.UserWS;
import com.ngbilling.core.security.JwtUtils;

@RestController
@RequestMapping("/api/signup")
public class SignupController {

	
	@Autowired
	PasswordEncoder encoder;

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	UserService userService;
	
    @Autowired
    private ModelMapper modelMapper;
	
	@PostMapping("/entity")
	public ResponseEntity<?> registerEntity(@RequestBody UserWS userWS){
		if("Admin".equals(userWS.getRole())) {
			if(userService.findByDescription(userWS.getContact().getOrganizationName())!=null) {
				return ResponseEntity
						.badRequest()
						.body(new MessageResponse("Error: company.already.exists"));
			}
			 if (!allowSignup()) {
		            return ResponseEntity
							.badRequest()
							.body(new MessageResponse("Error: Sign ups disabled on this jBilling host"));
		        }
		}
		if (userService.existsByUserName(userWS.getUserName())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userService.findEmail(userWS.getContact().getEmail())>0) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}
		
		CompanyDTO company = userService.createCompany(userWS);
		ContactDTO mappedEntity  = (ContactDTO)convertToEntity(userWS);
		ContactDTO companyContact = userService.createContact(mappedEntity, company,ServerConstants.TABLE_ENTITY);
		UserDTO userDTO = (UserDTO)convertToEntity(userWS);
		userDTO.setCompany(company);
		userDTO = userService.createAdminUser(userDTO);
		mappedEntity.setUserId(userDTO.getUserId());
		mappedEntity.setInclude(1);
		ContactDTO adminContact = userService.createContact(mappedEntity, company,ServerConstants.TABLE_BASE_USER);
		return ResponseEntity.ok(new MessageResponse(userWS.getUserName()+" User registered successfully!"));
	}
	

	 private Object convertToEntity(UserWS userWS){
		 Object contact = modelMapper.map(userWS, Object.class);
		 return contact;
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

			List roleList = userService.findRoleTypeIdAndCompanyId(defaultRole, company.getId());
			if (roleList!=null&&roleList.size()>0) {
				continue;
			}
			userService.createRole(defaultRole, company);
		}
	}
	
	private boolean allowSignup() {
        return jdbcTemplate.queryForObject("SELECT jb_allow_signup FROM jb_host_master", Boolean.class);
    }

}
