package com.cursoKotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@EnableAsync
@SpringBootApplication
class CursoKotlinApplication

fun main(args: Array<String>) {
	runApplication<CursoKotlinApplication>(*args)
}