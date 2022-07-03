package com.stockalert.tokyo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TokyoApplication {
}

fun main(args: Array<String>) {
    runApplication<TokyoApplication>(*args)
}
