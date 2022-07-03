package com.stockalert.seoul.vixindex.usecase

interface GetCurrentVixIndexUsage {
    operator fun invoke(): VixIndexResponse
}

class GetCurrentVixIndexUsageImpl(
    private val yahooFinanceService: YahooFinanceService
) : GetCurrentVixIndexUsage {
    companion object {
        private const val VIX_INDEX_SYMBOL = "^VIX"
    }

    override fun invoke(): VixIndexResponse = yahooFinanceService.getStockInfo(VIX_INDEX_SYMBOL).let {
        VixIndexResponse(
            currentPrice = it.currentPrice,
            openPrice = it.openPrice,
            closePrice = it.closePrice,
            marketStatus = it.marketState
        )
    }
}

data class VixIndexResponse(
    val currentPrice: Double,
    val openPrice: Double,
    val closePrice: Double,
    val marketStatus: String,
)