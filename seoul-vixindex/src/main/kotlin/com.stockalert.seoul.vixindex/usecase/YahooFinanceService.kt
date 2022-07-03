package com.stockalert.seoul.vixindex.usecase

interface YahooFinanceService {
    fun getStockInfo(symbol: String): StockPrice
}

data class StockPrice(
    val currency : String,
    val currentPrice: Double,
    val openPrice: Double,
    val closePrice: Double,
    val marketState: String
)