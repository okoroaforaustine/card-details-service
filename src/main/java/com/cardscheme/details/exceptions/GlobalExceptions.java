/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardscheme.details.exceptions;

import com.cardscheme.details.Utils.AppUtils;
import com.cardscheme.details.controller.CardSchemeController;
import com.cardscheme.details.controller.UserController;
import com.cardscheme.details.dto.ResponseError;
import com.cardscheme.details.dto.Error;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author austine.okoroafor
 */
@Slf4j
@RestControllerAdvice(assignableTypes = {CardSchemeController.class,UserController.class})
public class GlobalExceptions extends Exception {
    
    @Autowired
    AppUtils appUtils;

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<?> defaultErrorHandler(HttpServletRequest request, HttpServletResponse response,
            Exception e) {
        if (e instanceof AuthorizationException) {
            return appUtils.returnAuthorizationError(e);
        }

        log.error("Handling uncaught exception from application...", e);
        Error err = new Error(e.getMessage());
        List<Error> errs = new ArrayList<>();
        errs.add(err);

        ResponseError r = new ResponseError();
        r.setErrors(errs);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(r);
    }
    
    
    
}
