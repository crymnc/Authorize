package com.experiment.inventory.utils

import com.experiment.inventory.entity.base.BaseConstantEntity
import kotlin.reflect.KClass
import kotlin.reflect.jvm.internal.impl.resolve.calls.inference.CapturedType

class ConstantReference<T:BaseConstantEntity> {
    var name: String? = null
    var desc: String? = null
    var clazz: KClass<T>? = null
    var subConstants: List<ConstantReference<T>> = ArrayList()
}