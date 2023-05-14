package com.cursoKotlin.helper

import com.cursoKotlin.enuns.CustomerStatus
import com.cursoKotlin.enuns.Roles
import com.cursoKotlin.model.BookModel
import com.cursoKotlin.model.CustomerModel
import com.cursoKotlin.model.PurchaseModel
import java.math.BigDecimal
import java.util.*

fun buildCustomer(
    id: Int? = null,
    name: String = "customer name",
    email: String = "${UUID.randomUUID()}@email.com",
    password: String = "password"
) = CustomerModel(
    id = id,
    name = name,
    email = email,
    status = CustomerStatus.ATIVO,
    password = password,
    roles = setOf(Roles.CUSTOMER)
)

fun buildPurchase(
    id: Int? = null,
    customer: CustomerModel = buildCustomer(),
    books: MutableList<BookModel> = mutableListOf(),
    nfe: String? = UUID.randomUUID().toString(),
    price: BigDecimal = BigDecimal.TEN,
) = PurchaseModel (
    id = id,
    customer = customer,
    books = books,
    nfe = nfe,
    price = price
)