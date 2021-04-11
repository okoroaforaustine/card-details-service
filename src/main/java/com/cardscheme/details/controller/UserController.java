/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardscheme.details.controller;

import com.cardscheme.details.Utils.AppUtils;
import com.cardscheme.details.Utils.SecurityUtils;
import com.cardscheme.details.dto.Response;
import com.cardscheme.details.entity.AppUser;
import com.cardscheme.details.entity.CardScheme;
import com.cardscheme.details.repository.CardSchemeRepository;
import com.cardscheme.details.repository.UserRepository;
import com.cardscheme.details.service.CardService;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 *
 * @author austine.okoroafor
 */
@RestController
@RequestMapping("/User")
@Slf4j
@CrossOrigin

public class UserController {
    @Autowired
    CardService cardService;
    @Autowired
    AppUtils util;
     @Autowired
 UserRepository userRepo;
 
     @PostMapping(value = "/generateAPIKey", produces = "Application/json", consumes = "Application/json")
@ApiOperation(value = "Generate Hash Key",
            notes = "This generates API Key",
            response = Response.class)
    public ResponseEntity<?> generateHashKey(@Valid @RequestBody AppUser request, @ApiIgnore Errors errors) {

        if (errors.hasErrors()) {
            log.debug("Error:::: ");
            return util.returnPostValidationErrors(errors);
        }
      String apiKey=   SecurityUtils.generateUniquePaymentCode("123456789");
      request.setAppKey(apiKey);
           
        userRepo.save(request);

        return util.returnSuccessResponse(request, "API KEY Creatded succesfully");
    }
    
}
