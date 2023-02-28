package com.cursoKotlin.extension

import com.cursoKotlin.controller.request.PostBookRequest
import com.cursoKotlin.controller.request.PostCustomerRequest
import com.cursoKotlin.controller.request.PutBookRequest
import com.cursoKotlin.controller.request.PutCustomerRequest
import com.cursoKotlin.enuns.BookStatus
import com.cursoKotlin.enuns.CustomerStatus
import com.cursoKotlin.model.BookModel
import com.cursoKotlin.model.CustomerModel
import java.awt.print.Book

fun PostCustomerRequest.toCustomerModel(): CustomerModel {
    return CustomerModel(name = this.name, email = this.email, status = CustomerStatus.ATIVO)
}

fun PutCustomerRequest.toCustomerModel(previousValue: CustomerModel): CustomerModel {
    return CustomerModel(id = previousValue.id, name = this.name, email = this.email, status = previousValue.status)
}

fun PostBookRequest.toBookModel(customer: CustomerModel): BookModel {
    return BookModel(
        name = this.name,
        price = this.price,
        status = BookStatus.ATIVO,
        customer = customer
    )
}
fun PutBookRequest.toBookModel(previousValue: BookModel): BookModel {
    return BookModel(
        id = previousValue.id,
        name = this.name ?: previousValue.name,
        price = this.price ?: previousValue.price,
        status = previousValue.status,
        customer = previousValue.customer
    )
}