package com.cursoKotlin.mapper

import com.cursoKotlin.controller.dto.PostPurchaseRequest
import com.cursoKotlin.model.PurchaseModel
import com.cursoKotlin.service.BookService
import com.cursoKotlin.service.CustomerService
import org.springframework.stereotype.Component

@Component
class PurchaseMapper(
    private val bookService: BookService,
    private val customerService: CustomerService
) {
    fun toModel(request: PostPurchaseRequest): PurchaseModel {
       val customer =  customerService.getById(request.customerId)
       val books = bookService.findAllByIds(request.bookId)

        return PurchaseModel(
            cutomer = customer,
            book = books.toMutableList(),
            price = books.sumOf { it.price },
        )
    }
}