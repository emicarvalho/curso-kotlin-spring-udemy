package com.cursoKotlin.service

import com.cursoKotlin.model.CustomerModel
import com.cursoKotlin.repository.CustomerRrepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRrepository,
    val bookService: BookService
) {
    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun getById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow()
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
        customerRepository.deleteById(id)
    }
}