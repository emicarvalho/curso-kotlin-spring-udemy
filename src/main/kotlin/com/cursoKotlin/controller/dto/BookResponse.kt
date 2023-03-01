package com.cursoKotlin.controller.dto

import com.cursoKotlin.enuns.BookStatus
import com.cursoKotlin.model.CustomerModel
import java.math.BigDecimal

data class BookResponse(
    var id: Int? = null,

    var name: String,

    var price: BigDecimal,

    var customer: CustomerModel? = null,

    var status: BookStatus? = null
)
