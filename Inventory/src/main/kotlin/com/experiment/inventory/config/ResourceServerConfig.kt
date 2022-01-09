package com.experiment.inventory.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.token.RemoteTokenServices

@Configuration
@EnableResourceServer
class ResourceServerConfig: ResourceServerConfigurerAdapter() {

    override fun configure(resources : ResourceServerSecurityConfigurer){
        resources.resourceId("api")
    }

    @Bean
    fun createResourceServerTokenServices():RemoteTokenServices{
        val tokenServices = RemoteTokenServices()
        tokenServices.setCheckTokenEndpointUrl("http://localhost:8090/oauth/check_token")
        tokenServices.setClientId("testClientId")
        tokenServices.setClientSecret("secret")
        return tokenServices
    }
}