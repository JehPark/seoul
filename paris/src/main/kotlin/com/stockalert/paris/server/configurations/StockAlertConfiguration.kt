package com.stockalert.paris.server.configurations

import com.stockalert.seoul.infra.yahoofinancial.YahooFinancialHttpClient
import com.stockalert.seoul.vixindex.usecase.GetCurrentVixIndexUsageImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StockAlertConfiguration {
    companion object {
        private const val YAHOO_FINANCE_BASE_URL = "https://yfapi.net/v6/finance/"
    }

    @Bean
    fun yahooFinanceService() = YahooFinancialHttpClient(YAHOO_FINANCE_BASE_URL)

    @Bean
    fun getCurrentVixIndexPort() = GetCurrentVixIndexUsageImpl(yahooFinanceService())
}