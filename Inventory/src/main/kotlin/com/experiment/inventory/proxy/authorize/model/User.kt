package com.experiment.inventory.proxy.authorize.model

import java.util.*

class User {
    val id: Long? = null
    val createdBy: Long? = null
    var updatedBy:Long? = null
    val discontinueDate: Date? = null
    val createdAt: Date? = null
    var updatedAt: Date? = null
    val name: String? = null
    var lastName:String? = null
    val username: String? = null
    val password: String? = null
    val isActive = true
    val lastActivationDate: Date? = null
    var roles: MutableList<Long>? = null
    var userComponentContents: MutableList<UserComponentContent>? = null

    fun addRole(id: Long) {
        if (roles == null) roles = mutableListOf()
        roles!!.add(id)
    }

    fun addUserComponentContent(userComponentContent: UserComponentContent) {
        if (userComponentContents == null) userComponentContents = mutableListOf()
        userComponentContents!!.add(userComponentContent)
    }
}