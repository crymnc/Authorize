package com.experiment.authorize.util;

import com.experiment.authorize.config.web.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

public class OAuthUtils {

    public static Long getAuthenticatedUserId() {
        Object authenticationDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (authenticationDetails instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails oAuth2AuthenticationDetails = (OAuth2AuthenticationDetails) authenticationDetails;
            CustomUserDetails customUserDetails = (CustomUserDetails) oAuth2AuthenticationDetails.getDecodedDetails();
            return customUserDetails.getId();
        }
        return null;
    }
}
