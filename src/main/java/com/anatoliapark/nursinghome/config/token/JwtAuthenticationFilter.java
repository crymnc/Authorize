package com.anatoliapark.nursinghome.config.token;

import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.service.UserService;
import com.anatoliapark.nursinghome.util.Constants;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(Constants.HEADER_STRING);
        String username = null;
        String authToken = null;
        try {
            if (header != null && header.startsWith(Constants.TOKEN_PREFIX)) {
                authToken = header.replace(Constants.TOKEN_PREFIX, "");

                username = jwtTokenUtil.getUsernameFromToken(authToken);

            } else {
                logger.warn("couldn't find bearer string, will ignore the header");
            }
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserEntity user = userService.findUserByUsername(username).get();

                if (jwtTokenUtil.validateToken(authToken, user)) {
                    List<SimpleGrantedAuthority> auths = user.getAuthority();
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, auths);
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    logger.info("authenticated user " + username + ", setting security context");
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (IllegalArgumentException e) {
            logger.error("an error occured during getting username from token", e);
        } catch (ExpiredJwtException e) {
            String isRefreshToken = httpServletRequest.getHeader("isRefreshToken");
            String requestURL = httpServletRequest.getRequestURL().toString();
            // allow for Refresh Token creation if following conditions are true.
            if (isRefreshToken != null && isRefreshToken.equals("true") && requestURL.contains("refreshtoken")) {
                allowForRefreshToken(e, httpServletRequest);
            } else
                httpServletRequest.setAttribute("exception", e);
        } catch (SignatureException e) {
            logger.error("Authentication Failed. Username or Password not valid.");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private void allowForRefreshToken(ExpiredJwtException ex, HttpServletRequest request) {

        // create a UsernamePasswordAuthenticationToken with null values.
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                null, null, null);
        // After setting the Authentication in the context, we specify
        // that the current user is authenticated. So it passes the
        // Spring Security Configurations successfully.
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        // Set the claims so that in controller we will be using it to create
        // new JWT
        request.setAttribute("claims", ex.getClaims());

    }
}
