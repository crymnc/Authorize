package com.anatoliapark.nursinghome.controller.rest;

import com.anatoliapark.nursinghome.annotation.RestApiController;
import com.anatoliapark.nursinghome.config.token.JwtTokenUtil;
import com.anatoliapark.nursinghome.model.auth.User;
import com.anatoliapark.nursinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestApiController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/generate-token")
    public String register(@RequestBody User loginUser) throws AuthenticationException {

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = userService.findUserBy(loginUser.getUsername());
        return jwtTokenUtil.generateToken(user);

    }
}
