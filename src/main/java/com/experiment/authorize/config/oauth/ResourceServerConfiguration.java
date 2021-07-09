package com.experiment.authorize.config.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    public ResourceServerConfiguration() {
        super();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId("api");
        resources.tokenServices(createTokenServices());
    }

    @Bean
    public DefaultTokenServices createTokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(createTokenStore());
        return defaultTokenServices;
    }

    @Bean
    public TokenStore createTokenStore() {
        return new JwtTokenStore(createJwtAccessTokenConverter());
    }

    @Bean
    public CustomJwtAccessTokenConverter createCustomJwtConverter() {
        return new CustomJwtAccessTokenConverter();
    }

    @Bean
    public JwtAccessTokenConverter createJwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setAccessTokenConverter(createCustomJwtConverter());
        return converter;
    }
}
