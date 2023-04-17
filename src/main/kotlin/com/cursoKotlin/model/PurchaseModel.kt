package com.cursoKotlin.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne

@Entity(name = "purchase")
data class PurchaseModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int? = null,

    @ManyToOne
    @JoinColumn(name = "customer_id")
    val cutomer: CustomerModel,

    @ManyToMany
    @JoinTable(name = "purchase_book",
        joinColumns = [JoinColumn(name = "purchaseId")],
        inverseJoinColumns = [JoinColumn(name= "book_id")]
        )
    val book: MutableList<BookModel>,

    @Column
    val nfe: String? = null,

    @Column
    val price: BigDecimal,

    @Column(name = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()
)