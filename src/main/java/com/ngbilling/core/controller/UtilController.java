package com.ngbilling.core.controller;

import com.ngbilling.core.common.exception.MessageResponse;
import com.ngbilling.core.payload.request.user.UserWS;
import com.ngbilling.core.security.JwtUtils;
import com.ngbilling.core.server.persistence.dao.user.CompanyDAO;
import com.ngbilling.core.server.persistence.dao.user.RoleDAO;
import com.ngbilling.core.server.persistence.dao.user.UserDAO;
import com.ngbilling.core.server.persistence.dto.user.CompanyDTO;
import com.ngbilling.core.server.persistence.dto.user.RoleDTO;
import com.ngbilling.core.server.persistence.dto.user.UserDTO;
import com.ngbilling.core.server.persistence.dto.util.CurrencyDTO;
import com.ngbilling.core.server.persistence.dto.util.LanguageDTO;
import com.ngbilling.core.server.service.util.UtilService;
import com.ngbilling.core.server.util.ServerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/util")
@CrossOrigin(origins = "*", exposedHeaders = "X-Total-Count")
public class UtilController {

    @Autowired
    private UtilService utilService;

    @GetMapping("/countries")
    public List getAllCountries(){
        return utilService.findAllCountries(ServerConstants.LANGUAGE_ENGLISH_ID);
    }
    @GetMapping("/currencies")
    public List getAllCurrencies(){
        return utilService.findAllCurrencies(ServerConstants.LANGUAGE_ENGLISH_ID);
    }
    @GetMapping("/languages")
    public List getAllLanguages(){
        return utilService.findAllLanguages(ServerConstants.LANGUAGE_ENGLISH_ID);
    }
}
