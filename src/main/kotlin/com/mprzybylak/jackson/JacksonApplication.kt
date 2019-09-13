package com.mprzybylak.jackson

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class JacksonApplication

fun main(args: Array<String>) {
	runApplication<JacksonApplication>(*args)
}
