package com.cursoKotlin.service

import com.cursoKotlin.exception.AuthenticationException
import com.cursoKotlin.repository.CustomerRepository
import com.cursoKotlin.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDatailsCustomerService(
    private val customerRepository: CustomerRepository
): UserDetailsService {
    override fun loadUserByUsername(id: String): UserDetails {
        val customer = customerRepository.findById(id.toInt())
            .orElseThrow { AuthenticationException("Usuário não encontrado", "999") }
        return UserCustomDetails(customer)
    }
}