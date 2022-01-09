package com.experiment.inventory.exception

class BusinessException : RuntimeException{
    private val serialVersionUID = 51211382562L

    constructor() : super()

    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(message: String) : super(message)

    constructor(throwable: Throwable) : super(throwable)
}