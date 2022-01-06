package com.experiment.accounting.utils;

import com.experiment.accounting.proxy.authorize.AuthorizeProxy;
import com.experiment.accounting.proxy.authorize.model.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthUtils {

    public static User getAuthenticatedUser(){
        OAuth2Authentication authentication = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        return BeanUtil.getBean(AuthorizeProxy.class).getUserByUsername(authentication.getUserAuthentication().getName());
    }
}
