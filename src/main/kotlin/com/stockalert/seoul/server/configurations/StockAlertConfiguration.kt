package com.stockalert.seoul.server.configurations

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StockAlertConfiguration {
    @Bean
    fun getCurrentVixIndex() = GetCurrentVixIndex()
}