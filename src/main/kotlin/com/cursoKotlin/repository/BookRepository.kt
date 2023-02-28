package com.cursoKotlin.repository

import com.cursoKotlin.enuns.BookStatus
import com.cursoKotlin.model.BookModel
import com.cursoKotlin.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface BookRepository: CrudRepository<BookModel, Int> {
    fun findByStatus(status: BookStatus): List<BookModel>
    fun findByCustomer(customer: CustomerModel): List<BookModel>
}