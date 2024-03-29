package com.cursoKotlin.security

import com.cursoKotlin.exception.AuthenticationException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val  jwtUtil: JwtUtil,
    private val userDetaisl : UserDetailsService
): BasicAuthenticationFilter(authenticationManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
       val authorization =  request.getHeader("Authorization")
        if(authorization != null && authorization.startsWith("Bearer")) {
            val auth = getAuthetication(authorization.split(" ")[1])
            SecurityContextHolder.getContext().authentication = auth
        }
        chain.doFilter(request, response)
    }

    private fun getAuthetication(token: String): UsernamePasswordAuthenticationToken {
        if(!jwtUtil.isValidToken(token)) {
            throw AuthenticationException("Token inválido", "999")
        }
        val subject = jwtUtil.getSubject(token)
        val customer = userDetaisl.loadUserByUsername(subject)
        return UsernamePasswordAuthenticationToken(customer, null, customer.authorities)
    }
}