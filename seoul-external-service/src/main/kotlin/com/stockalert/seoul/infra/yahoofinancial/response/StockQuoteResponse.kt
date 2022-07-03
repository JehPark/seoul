package com.stockalert.seoul.infra.yahoofinancial.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import okhttp3.internal.http2.ErrorCode

@JsonIgnoreProperties(ignoreUnknown = true)
data class StockQuoteResponse(
    val error: ErrorCode? = null,
    val quoteResponse: StockQuoteBody? = null
)