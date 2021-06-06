package com.anatoliapark.nursinghome.controller.rest;

import com.anatoliapark.nursinghome.annotation.RestApiController;
import com.anatoliapark.nursinghome.config.token.JwtTokenUtil;
import com.anatoliapark.nursinghome.config.token.Token;
import com.anatoliapark.nursinghome.entity.auth.UserEntity;
import com.anatoliapark.nursinghome.exception.BusinessExceptions;
import com.anatoliapark.nursinghome.service.UserService;
import io.jsonwebtoken.impl.DefaultClaims;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestApiController
@RequestMapping("/api")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserService userService;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping(value = "/authenticate")
    public @ResponseBody Token authenticate(@RequestHeader("username") String username, @RequestHeader("password") String password) throws AuthenticationException {
        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(auth);
        UserEntity user = userService.findUserByUsername(username).orElseThrow(() -> BusinessExceptions.USER_NOT_FOUND);
        Token token = new Token();
        token.setUsername(user.getUsername());
        token.setName(user.getName());
        token.setLastName(user.getLastName());
        token.setToken(jwtTokenUtil.generateToken(user));
        return token;
    }

    @PostMapping(value = "/refreshtoken")
    public ResponseEntity<Token> refreshToken(HttpServletRequest request) {
        DefaultClaims claims = (io.jsonwebtoken.impl.DefaultClaims) request.getAttribute("claims");
        Map<String, Object> expectedMap = getMapFromIoJsonwebtokenClaims(claims);
        UserEntity user = userService.findUserByUsername(expectedMap.get("sub").toString()).orElseThrow(() -> BusinessExceptions.USER_NOT_FOUND);
        Token token = new Token();
        token.setUsername(user.getUsername());
        token.setName(user.getName());
        token.setLastName(user.getLastName());
        token.setToken(jwtTokenUtil.doGenerateRefreshToken(expectedMap, expectedMap.get("sub").toString()));
        return ResponseEntity.ok(token);
    }

    public Map<String, Object> getMapFromIoJsonwebtokenClaims(DefaultClaims claims) {
        Map<String, Object> expectedMap = new HashMap<String, Object>();
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            expectedMap.put(entry.getKey(), entry.getValue());
        }
        return expectedMap;
    }
}
