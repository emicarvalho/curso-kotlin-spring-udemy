package com.cursoKotlin.controller.dto

class PageResponse<T>(
    var items: List<T>,
    var currentePage: Int,
    var totalItems: Long,
    var totalPages: Int,
)