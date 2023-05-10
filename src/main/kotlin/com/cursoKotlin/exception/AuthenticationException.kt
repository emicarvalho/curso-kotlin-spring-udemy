package com.cursoKotlin.exception

class AuthenticationException(override val message: String, val errorCode: String): Exception() {
}