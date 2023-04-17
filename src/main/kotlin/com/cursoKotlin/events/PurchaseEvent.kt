package com.cursoKotlin.events

import com.cursoKotlin.model.PurchaseModel
import org.springframework.context.ApplicationEvent

class PurchaseEvent (
    source: Any,
    val purchaseModel: PurchaseModel
) : ApplicationEvent(source)