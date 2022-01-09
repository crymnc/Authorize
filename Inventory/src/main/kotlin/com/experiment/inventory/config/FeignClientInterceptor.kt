package com.experiment.inventory.config

import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails
import org.springframework.stereotype.Component

@Component
 class FeignClientInterceptor : RequestInterceptor {
    private val AUTHORIZATION_HEADER = "Authorization"
    private val TOKEN_TYPE = "Bearer"

    override fun apply(requestTemplate: RequestTemplate) {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication != null && authentication.details is OAuth2AuthenticationDetails) {
            val details = authentication.details as OAuth2AuthenticationDetails
            requestTemplate.header(AUTHORIZATION_HEADER, "$TOKEN_TYPE ${details.tokenValue}")
        }
    }


}