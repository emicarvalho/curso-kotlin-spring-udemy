package com.cursoKotlin.controller.dto

import org.springframework.validation.FieldError

data class ErrorResponse(
    var httpStatus: Int,
    var message: String,
    var internalCode: String,
    var erros: List<FieldErrorResponse>?
)
