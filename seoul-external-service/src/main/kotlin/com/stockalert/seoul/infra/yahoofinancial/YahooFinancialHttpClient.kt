package com.stockalert.seoul.infra.yahoofinancial

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.stockalert.seoul.retrofit.orElseThrow
import com.stockalert.seoul.vixindex.usecase.StockPrice
import com.stockalert.seoul.vixindex.usecase.YahooFinanceService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.time.Duration
import retrofit2.converter.jackson.JacksonConverterFactory
import java.io.IOException

class YahooFinancialHttpClient(
    baseUrl: String
) : YahooFinanceService {
    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(Duration.ofSeconds(2))
        .readTimeout(Duration.ofSeconds(1))
        .build()

    private val yahoofinancialClient = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
        .build()
        .create(YahooFinancialHttp::class.java)

    override fun getStockInfo(symbol: String): StockPrice = yahoofinancialClient.getStockInfo(
        symbols = symbol
    ).orElseThrow {
        IOException("it fails to connect yahoo finance api: ${it.code()}")
    }.let {
        it.quoteResponse!!.result[0].let { response ->
            StockPrice(
                currency = response.currency,
                currentPrice = response.regularMarketPrice,
                openPrice = response.regularMarketOpen,
                closePrice = response.regularMarketPreviousClose,
                marketState = response.marketState
            )
        }
    }
}