package com.cursoKotlin.validation

import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass


@Constraint(validatedBy = [EmailAvailableValidator::class])
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class EmailAvailable(
    val message: String = "E-mail já cadastrado",
    val group: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
