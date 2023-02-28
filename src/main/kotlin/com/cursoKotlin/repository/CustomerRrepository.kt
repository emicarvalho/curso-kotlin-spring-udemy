package com.cursoKotlin.repository

import com.cursoKotlin.model.CustomerModel
import org.springframework.data.repository.CrudRepository

interface CustomerRrepository: CrudRepository<CustomerModel, Int> {
    fun findByNameContaining(name: String): List<CustomerModel>
}