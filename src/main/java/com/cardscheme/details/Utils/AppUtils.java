/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardscheme.details.Utils;

import com.cardscheme.details.dto.Payload;
import com.cardscheme.details.dto.Response;
import com.cardscheme.details.dto.Error;
import com.cardscheme.details.dto.ResponseError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import com.cardscheme.details.paginationdto.payloads;
import java.util.Calendar;
import java.util.TimeZone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 *
 * @author austine.okoroafor
 */
@Component
@Slf4j
public class AppUtils {
     public static String countString(String appId,String hashKey) {

        int getPos = appId.length();
       String  getHashValue=hashKey.substring(getPos, hashKey.length());
       
       System.out.println("subString "+ getHashValue);
        return getHashValue;

    }
    
     public static long getCurrentTime() {
        TimeZone lagosTimeZone = TimeZone.getTimeZone("Africa/Lagos");
        Calendar calendar = Calendar.getInstance(lagosTimeZone);
        return calendar.getTimeInMillis() / 1000;
    }
    
      public ResponseEntity<?> returnAuthorizationError(Exception e) {
        log.error("Returning authorization error to client");
        List<Error> errors = new ArrayList<Error>();
        errors.add(new Error("Unauthorized. " + e.getMessage()));
        ResponseError response = new ResponseError();
        response.setErrors(errors);

        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<ResponseError>(response, httpHeaders, HttpStatus.UNAUTHORIZED);
    }
    
       public ResponseEntity<Response> returnErrorResponse(List<Error> errors, HttpStatus code) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Response response = new Response();
        response.setStatus(ConstantUtil.ERROR);
        response.setErrors(errors);
        return new ResponseEntity<Response>(response, httpHeaders, code);
    }

    public ResponseEntity<Response> returnErrorResponse(String error, HttpStatus code) {
        List<Error> errors = new ArrayList<Error>();
        errors.add(new Error(error));
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Response response = new Response();
        response.setStatus(ConstantUtil.ERROR);
        response.setErrors(errors);
        return new ResponseEntity<Response>(response, httpHeaders, code);
    }

    public ResponseEntity<Response> returnPostValidationErrors(Errors errors) {
        //log.debug("Returning post validation errors...");
        List<FieldError> fields = errors.getFieldErrors();
        List<Error> errorList = new ArrayList<Error>();
        Iterator<FieldError> eIt = fields.iterator();
        while (eIt.hasNext()) {
            FieldError fe = eIt.next();
            errorList.add(getFieldRequiredError(fe.getField()));
        }
        return returnErrorResponse(errorList, HttpStatus.BAD_REQUEST);
    }
    
      public Error getFieldRequiredError(String field) {
        return new Error("Required request parameter is not present  " + field);
    }

    

    public ResponseEntity<Payload> returnSuccessResponse(Object responseObj, String message) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        Payload payload = new Payload();
        payload.setSuccess(ConstantUtil.SUCCESS);

        payload.setPayload(responseObj);
        return new ResponseEntity<Payload>(payload, httpHeaders, HttpStatus.OK);
    }

    public ResponseEntity<payloads> returnSuccessResponse(Object responseObj, int start, int limit, int size) {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        payloads payload = new payloads();
        payload.setSuccess(ConstantUtil.SUCCESS);
        payload.setStart(start);
        payload.setLimit(limit);
        payload.setSize(size);
        payload.setPayload(responseObj);
        return new ResponseEntity<payloads>(payload, httpHeaders, HttpStatus.OK);
    }
}
