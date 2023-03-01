package com.cursoKotlin.model

import com.cursoKotlin.enuns.BookStatus
import java.math.BigDecimal
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "book")
data class BookModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    @Column
    var name: String,

    @Column
    var price: BigDecimal,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel? = null
) {
    @Column
    @Enumerated(EnumType.STRING)
    var status: BookStatus? = null
        set(value) {
            if (field == BookStatus.CANCELADO || field == BookStatus.DELETADO)
                throw Exception("Não é possível alterar um livro com status ${field}")
            field = value
        }

    constructor(id: Int? = null,
                name: String,
                price: BigDecimal,
                status: BookStatus?,
                customer: CustomerModel? = null): this(id, name, price, customer) {
                    this.status = status
                }
}