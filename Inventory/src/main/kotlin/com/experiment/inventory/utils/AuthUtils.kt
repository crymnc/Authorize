package com.experiment.inventory.utils

import com.experiment.inventory.proxy.authorize.AuthorizeProxy
import com.experiment.inventory.proxy.authorize.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.stereotype.Service

@Service
class AuthUtils {

    companion object{
        fun getAuthenticatedUser(): User {
            val authentication = SecurityContextHolder.getContext().authentication as OAuth2Authentication
            return BeanUtil.getBean(AuthorizeProxy::class.java)?.getUserByUsername(authentication.userAuthentication.name) ?: User()
        }
    }

}