package com.cursoKotlin.extension

import com.cursoKotlin.controller.dto.PostBookRequest
import com.cursoKotlin.controller.dto.PostCustomerRequest
import com.cursoKotlin.controller.dto.PutBookRequest
import com.cursoKotlin.controller.dto.PutCustomerRequest
import com.cursoKotlin.controller.dto.BookResponse
import com.cursoKotlin.controller.dto.CustomerResponse
import com.cursoKotlin.enuns.BookStatus
import com.cursoKotlin.enuns.CustomerStatus
import com.cursoKotlin.model.BookModel
import com.cursoKotlin.model.CustomerModel

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

fun CustomerModel.toResponse(): CustomerResponse {
        return CustomerResponse(
            id = this.id,
            name = this.name,
            email = this.email,
            status = this.status
        )
}

fun BookModel.toResponse(): BookResponse {
    return  BookResponse(
        id = this.id,
        name = this.name,
        customer = this.customer,
        price = this.price,
        status = this.status
    )
}
