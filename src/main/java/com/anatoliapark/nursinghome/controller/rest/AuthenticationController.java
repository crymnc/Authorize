package com.anatoliapark.nursinghome.controller.rest;

import com.anatoliapark.nursinghome.annotation.RestApiController;
import com.anatoliapark.nursinghome.config.token.JwtTokenUtil;
import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.model.Token;
import com.anatoliapark.nursinghome.model.User;
import com.anatoliapark.nursinghome.service.ConstantService;
import com.anatoliapark.nursinghome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestApiController
@RequestMapping("/api")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Autowired
    private ConstantService constantService;

    @PostMapping(value = "/authenticate")
    public @ResponseBody Token authenticate(@RequestBody UserEntity loginUser) throws AuthenticationException {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = userService.findUserByUsername(loginUser.getUsername());
        Token token = new Token();
        token.setUsername(user.getUsername());
        token.setName(user.getName());
        token.setLastName(user.getLastName());
        token.setToken(jwtTokenUtil.generateToken(user));
        return token;
    }
}
