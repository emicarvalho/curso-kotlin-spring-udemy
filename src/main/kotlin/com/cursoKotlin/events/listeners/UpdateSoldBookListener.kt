package com.cursoKotlin.events.listeners

import com.cursoKotlin.events.PurchaseEvent
import com.cursoKotlin.service.BookService
import com.cursoKotlin.service.PurchaseService
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UpdateSoldBookListener(
    private val bookService: BookService
) {

    @Async
    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {
        bookService.purchase(purchaseEvent.purchaseModel.book)
    }
}