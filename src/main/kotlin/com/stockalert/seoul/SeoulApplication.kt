package com.stockalert.seoul

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SeoulApplication

fun main(args: Array<String>) {
	runApplication<SeoulApplication>(*args)
}
