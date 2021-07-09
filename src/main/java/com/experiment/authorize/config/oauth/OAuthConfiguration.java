package com.experiment.authorize.config.oauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userService;
    private final JwtAccessTokenConverter jwtAccessTokenConverter;
    private final DataSource dataSource;

    public OAuthConfiguration(AuthenticationManager authenticationManager, UserDetailsService userService, JwtAccessTokenConverter jwtAccessTokenConverter, DataSource dataSource) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtAccessTokenConverter = jwtAccessTokenConverter;
        this.dataSource = dataSource;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource);
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                .accessTokenConverter(jwtAccessTokenConverter)
                .userDetailsService(userService)
                .authenticationManager(authenticationManager);
    }

}
