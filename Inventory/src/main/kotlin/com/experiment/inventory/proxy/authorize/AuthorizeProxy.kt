package com.experiment.inventory.proxy.authorize

import com.experiment.inventory.proxy.authorize.model.User
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "authorize")
interface AuthorizeProxy {

    @GetMapping("/api/users/username/{username}")
    fun getUserByUsername(@PathVariable(name = "username") username: String?): User?

    @GetMapping("/api/users/user-id/{user-id}")
    fun getUserById(@PathVariable(name = "user-id") userId: Long?): User?
}