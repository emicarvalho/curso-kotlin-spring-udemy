package com.cursoKotlin.service

import com.cursoKotlin.enuns.BookStatus
import com.cursoKotlin.model.BookModel
import com.cursoKotlin.model.CustomerModel
import com.cursoKotlin.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    val bookRepository: BookRepository
) {
    fun create(book: BookModel) = bookRepository.save(book)

    fun findAll(pageable: Pageable): Page<BookModel> {
       return  bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel> = bookRepository.findByStatus(BookStatus.ATIVO, pageable)

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