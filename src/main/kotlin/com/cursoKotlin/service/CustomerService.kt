package com.cursoKotlin.service

import com.cursoKotlin.enuns.CustomerStatus
import com.cursoKotlin.enuns.Errors
import com.cursoKotlin.exception.NotFoundException
import com.cursoKotlin.model.CustomerModel
import com.cursoKotlin.repository.CustomerRrepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRrepository,
    val bookService: BookService
) {
    fun getAll(name: String?, pageable: Pageable): Page<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it, pageable)
        }
        return customerRepository.findAll(pageable)
    }

    fun getById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow{ NotFoundException(Errors.ML202.code, Errors.ML202.message.format(id)) }
    }

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun update(customer: CustomerModel) {
        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = getById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
       return !customerRepository.existsByEmail(email)
    }
}