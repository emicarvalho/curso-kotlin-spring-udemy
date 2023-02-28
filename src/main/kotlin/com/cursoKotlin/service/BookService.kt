package com.cursoKotlin.service

import com.cursoKotlin.enuns.BookStatus
import com.cursoKotlin.model.BookModel
import com.cursoKotlin.model.CustomerModel
import com.cursoKotlin.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun create(book: BookModel) = bookRepository.save(book)

    fun findAll(): List<BookModel> = bookRepository.findAll().toList()

    fun findActives(): List<BookModel> = bookRepository.findByStatus(BookStatus.ATIVO)

    fun findById(id: Int): BookModel = bookRepository.findById(id).orElseThrow()

    fun update(book: BookModel) = bookRepository.save(book)

    fun delete(id: Int) {
        val book = findById(id)

        book.status = BookStatus.CANCELADO

        update(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)
        for(book in books) {
            book.status = BookStatus.DELETADO
        }
        bookRepository.saveAll(books)
    }
}