package com.ngbilling.core.controller;

import com.ngbilling.core.facade.UserFacade;
import com.ngbilling.core.server.service.util.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/entity")
public class EntityController {
    @Autowired
    private UtilService utilService;

    @Autowired
    private UserFacade userFacade;

    @GetMapping("/accountTypes")
    public List getAllAccountTypes(){
         return utilService.findEntityAccountTypes( userFacade.getAuthentication().getCompanyId());
    }
}
