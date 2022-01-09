package com.experiment.inventory.utils

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Service

@Service
class BeanUtil:ApplicationContextAware {

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        context = applicationContext
    }

    companion object{
        var context: ApplicationContext? = null

        fun <T> getBean(beanClass: Class<T>): T? {
            return this.context?.getBean(beanClass)
        }
    }
}