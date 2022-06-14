package com.ngbilling.core.controller;

import com.ngbilling.core.common.response.MessageResponse;
import com.ngbilling.core.facade.UserFacade;
import com.ngbilling.core.facade.impl.UserFacadeImpl;
import com.ngbilling.core.payload.request.user.UserWS;
import com.ngbilling.core.server.persistence.dto.audit.EventLogAPIDTO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;
import com.ngbilling.core.server.service.user.UserService;
import com.ngbilling.core.server.service.util.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/signup")
@CrossOrigin(origins = "*", exposedHeaders = "X-Total-Count", maxAge = 3600)
public class SignupController {


    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private UtilService utilService;
    @Autowired
    private UserFacade userFacade;



    @PostMapping("/entity")
    public ResponseEntity<?> registerEntity(@RequestBody UserWS userWS) {
        ResponseEntity responseEntity =  null;
        Date requestTime = new Date();
        if (userService.findByDescription(userWS.getContact().getOrganizationName()) != null) {
                    responseEntity = ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Error: company.already.exists"));
                    return responseEntity;
                }
                if (!allowSignup()) {
                    responseEntity = ResponseEntity
                            .badRequest()
                            .body(new MessageResponse("Error: Sign ups disabled on this jBilling host"));
                    return responseEntity;
                }
           if (userService.existsByUserName(userWS.getUserName())) {
                responseEntity = ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Username is already taken!"));
                return responseEntity;
            }
            userFacade.createCompany(userWS);
            responseEntity = ResponseEntity.ok(new MessageResponse(userWS.getUserName() + " User registered successfully!"));
            return responseEntity;

    }


    private UserDTO createUser(LanguageDTO language, CurrencyDTO currency, CompanyDTO company) {
        UserDTO userDTO = new UserDTO();
        return userDTO;
    }


    private boolean allowSignup() {
        return jdbcTemplate.queryForObject("SELECT jb_allow_signup FROM jb_host_master", Boolean.class);
    }

    @PostMapping("/user")
    public ResponseEntity<?> registerUser(@RequestBody UserWS userWS) {
        Date requestTime = new Date();
        ResponseEntity responseEntity =  null;
        try {
            return null;
        }finally{
            EventLogAPIDTO eventLogAPIDTO =  new EventLogAPIDTO();
            eventLogAPIDTO.setApiName("registerUser");
            eventLogAPIDTO.setResponseDatetime(new Date());
            eventLogAPIDTO.setRequestDatetime(requestTime);
            eventLogAPIDTO.setRequest(userWS.toString());
            eventLogAPIDTO.setResponse(responseEntity!=null?responseEntity.toString():null);
            eventLogAPIDTO.setUserName(userWS.getUserName());
            utilService.createEventLogAPI(eventLogAPIDTO);
        }
    }

    @PostMapping("/staff")
    public ResponseEntity<?> registerStaff(@RequestBody UserWS userWS) {
        Date requestTime = new Date();
        ResponseEntity responseEntity =  null;
        try {
            return null;
        }finally{
            EventLogAPIDTO eventLogAPIDTO =  new EventLogAPIDTO();
            eventLogAPIDTO.setApiName("createRegisterUser");
            eventLogAPIDTO.setResponseDatetime(new Date());
            eventLogAPIDTO.setRequestDatetime(requestTime);
            eventLogAPIDTO.setRequest(userWS.toString());
            eventLogAPIDTO.setResponse(responseEntity!=null?responseEntity.toString():null);
            eventLogAPIDTO.setUserName(userWS.getUserName());
            utilService.createEventLogAPI(eventLogAPIDTO);
        }
    }
}
