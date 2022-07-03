package com.stockalert.seoul.infra.yahoofinancial

import com.stockalert.seoul.infra.yahoofinancial.response.StockQuoteResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface YahooFinancialHttp {
    @GET("quote")
    fun getStockInfo(
        @Header("X-API-KEY") apiKey : String = X_API_KEY,
        @Query("symbols") symbols: String,
        @Query("region") region: String? = REGION,
        @Query("lang") language: String? = LANG,
    ): Call<StockQuoteResponse>

    companion object {
        private const val X_API_KEY = "GW0HOYZcbI7RMLYkOnLSU1vo3YFI9Hpb6JLoTc1O"
        private const val REGION = "US"
        private const val LANG = "en"
    }
}