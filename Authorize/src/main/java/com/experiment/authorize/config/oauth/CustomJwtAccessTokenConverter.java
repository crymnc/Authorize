package com.experiment.authorize.config.oauth;

import com.experiment.authorize.config.CustomUserDetailsService;
import com.experiment.authorize.config.web.CustomUserDetails;
import com.experiment.authorize.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

public class CustomJwtAccessTokenConverter extends DefaultAccessTokenConverter implements JwtAccessTokenConverterConfigurer {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(JwtAccessTokenConverter converter) {
        converter.setAccessTokenConverter(this);
        converter.setSigningKey(Constants.JWT_SIGNING_KEY);
    }

    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication auth = super.extractAuthentication(map);
        CustomUserDetails userDetails = (CustomUserDetails) customUserDetailsService.loadUserByUsername(map.get("user_name").toString());
        auth.setDetails(userDetails);
        return auth;
    }
}
