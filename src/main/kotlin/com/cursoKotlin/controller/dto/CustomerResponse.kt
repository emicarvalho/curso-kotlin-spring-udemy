package com.cursoKotlin.controller.dto

import com.cursoKotlin.enuns.CustomerStatus

data class CustomerResponse(
    var id: Int? = null,
    var name: String,
    var email: String,
    var status: CustomerStatus
)
