package com.experiment.inventory.service.base

abstract class BaseService {
    protected open fun <T> createInstance(c: Class<T>): T {
        return c.getDeclaredConstructor().newInstance()
    }
}