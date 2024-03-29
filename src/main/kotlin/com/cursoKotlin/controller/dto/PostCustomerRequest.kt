package com.cursoKotlin.controller.dto

import com.cursoKotlin.validation.EmailAvailable
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class PostCustomerRequest(

    @field:NotEmpty(message = "Nome nao pode ficar vazio")
    var name: String,

    @field:Email(message = "e-mail deve ser válido")
    var email: String,

    @field:NotEmpty(message = "password deve ser válido")
    var password: String
)