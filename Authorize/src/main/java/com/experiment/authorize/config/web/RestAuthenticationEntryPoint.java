package com.experiment.authorize.config.web;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The Entry Point will not redirect to any sort of Login - it will return the 401
 */
@Component
public final class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(
        final HttpServletRequest request, 
        final HttpServletResponse response, 
        final AuthenticationException authException) throws IOException {
        if (request.getAttribute("exception") != null) {
            ExpiredJwtException ex = (ExpiredJwtException) request.getAttribute("exception");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Username or password is wrong");
        }
    }

}