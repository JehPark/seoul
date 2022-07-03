package com.stockalert.seoul.infra.yahoofinancial.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class StockQuoteBody(
    val result : List<Quote> = emptyList()
)