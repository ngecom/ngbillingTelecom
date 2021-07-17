package com.ngbilling.core.facade.impl;

import com.ngbilling.core.facade.UserFacade;
import com.ngbilling.core.payload.request.user.CompanyUserDetails;
import com.ngbilling.core.payload.request.user.UserWS;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;
import com.ngbilling.core.server.service.user.UserService;
import com.ngbilling.core.server.service.util.UtilService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UtilService utilService;
    @Autowired
    private PasswordEncoder encoder;

    public UserDTO createCompany(UserWS userWS){
        UserDTO userDTO  = convertToDTO(userWS);
        LanguageDTO language = utilService.findByLanguageCode(userWS.getLanguageCode());
        CurrencyDTO currency = utilService.findByCurrencyCode(userWS.getCurrencyCode());
        userDTO.setLanguage(language);
        userDTO.setCurrency(currency);
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        userDTO.getContact().setCreateDate(new Date());
        userService.createCompany(userDTO);
        return userDTO;
    }
    private UserDTO convertToDTO(UserWS userWS) {
        return modelMapper.map(userWS, UserDTO.class);
    }

    private UserWS convertToPayload(UserDTO entityDTO) {
        return modelMapper.map(entityDTO, UserWS.class);
    }

    @Override
    public CompanyUserDetails getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CompanyUserDetails companyUserDetails = (CompanyUserDetails) authentication.getPrincipal();
        return companyUserDetails;
    }
}
