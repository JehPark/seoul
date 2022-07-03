package com.stockalert.seoul.infra.yahoofinancial.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Quote(
    val ask: Double = 0.0,
    val displayName: String = "",
    val longName: String = "",
    val currency: String = "",
    val exchangeTimezoneShortName: String = "",
    val regularMarketOpen: Double = 0.0,
    val regularMarketPrice: Double = 0.0,
    val regularMarketPreviousClose: Double = 0.0,
    val marketState: String = "",
)