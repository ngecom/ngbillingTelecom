package com.ngbilling.core.controller;

import com.ngbilling.core.server.service.util.UtilService;
import com.ngbilling.core.server.util.ServerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
