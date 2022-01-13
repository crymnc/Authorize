package com.experiment.inventory.exception

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

class ApiError {
    var status: String? = null
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    var timestamp: LocalDateTime? = null
    var message: String? = null
    var debugMessage: String? = null
}