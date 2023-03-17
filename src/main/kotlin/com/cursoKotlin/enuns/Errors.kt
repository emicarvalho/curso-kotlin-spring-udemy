package com.cursoKotlin.enuns

enum class Errors(val code:String, val message: String) {
    ML001("ML-001", "Invalid request"),
    ML101("ML101", "Book %s not exists"),
    ML102("ML-102", "Não é possível alterar um livro com status %s"),
    ML202("ML202", "Customer %s not exists")
}
