/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cardscheme.details.interceptor;

import com.cardscheme.details.Utils.ConstantUtil;
import com.cardscheme.details.Utils.SecurityUtils;
import com.cardscheme.details.entity.AppUser;
import com.cardscheme.details.exceptions.AuthorizationException;
import com.cardscheme.details.repository.UserRepository;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author austine.okoroafor
 */
@Component
@Slf4j
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserRepository appUserRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse resposne, Object o) throws Exception {
        String authorization = request.getHeader("authorization");
        String appKey = request.getHeader("appKey");
        String timeStamp = request.getHeader("timeStamp");

        log.info("authorization {} ", authorization);
        log.info("appKey {} ", appKey);
        log.info("timestamp {} ", timeStamp);

        if (null == authorization || (null == appKey || null == timeStamp)) {
            throw new AuthorizationException("Invalid message request");

        }
        AppUser appUser = appUserRepository.findByAppKey(appKey);
         String appId="";
        if (appUser == null) {
            throw new AuthorizationException("Required appkey header not valid ");

        }else{
        
            appId=appUser.getAppID();
        }

        String sign = SecurityUtils.encode(appKey, timeStamp);
        log.info("sign ::: " + sign);
        String auth =  appId.concat(sign);
        log.info("auth ::: " + auth);

        if (auth.equals(authorization)) {
            return true;
        }
        if (!auth.equals(authorization)) {
            throw new AuthorizationException("you are not authorized");
        }
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest hsr, HttpServletResponse hsr1, Object o, ModelAndView mav)
            throws Exception {

    }

}
