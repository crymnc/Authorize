package com.experiment.inventory.service.base

import kotlin.reflect.KClass
import kotlin.reflect.full.createInstance

abstract class BaseService {
    protected open fun <T : Any> createInstance(c: KClass<T>): T {
        return c.createInstance()
    }
}