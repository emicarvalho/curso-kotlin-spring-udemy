package com.cursoKotlin.controller.dto

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PutCustomerRequest (
    @field:NotEmpty(message = "Nome nao pode ficar vazio")
    var name: String,

    @field:Email(message = "e-mail deve ser válido")
    var email: String
)