package com.cursoKotlin.controller.dto

data class ErrorResponse(
    var httpCode: Int,
    var message: String,
    var internalCode: String,
    var erros: List<FieldErrorResponse>?
)
