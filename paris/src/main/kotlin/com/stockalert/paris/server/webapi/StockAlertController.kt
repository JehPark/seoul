package com.stockalert.paris.server.webapi

import com.stockalert.paris.server.response.Response
import com.stockalert.seoul.vixindex.usecase.GetCurrentVixIndexUsage
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/vix")
class StockAlertController(
    private val getCurrentVixIndexPort: GetCurrentVixIndexUsage
) {
    companion object {
        private val DAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    }

    @GetMapping("/index")
    fun getVixIndex(): VixIndexResponse {
        val vixIndex = getCurrentVixIndexPort()
        if (vixIndex.currentPrice == 0.0){
            return VixIndexResponse(Response.error)
        }
        return VixIndexResponse(
            Response.success,
            VixIndexResponse.VixIndex(
                vixIndex.currentPrice,
                vixIndex.openPrice,
                vixIndex.closePrice,
                vixIndex.marketStatus,
                LocalDateTime.now().format(DAY_FORMATTER)
            )
        )
    }

    @GetMapping("/timetobuy")
    fun getTimeToBuy(@RequestParam targetValue: Double): ResponseIsTimeToBuy{
        val vixIndex = getCurrentVixIndexPort()
        if (vixIndex.currentPrice == 0.0) {
            return ResponseIsTimeToBuy(
                Response.error
            )
        }
        return ResponseIsTimeToBuy(
            Response.success,
            vixIndex.currentPrice > targetValue
        )
    }

    data class ResponseIsTimeToBuy(
        val code: Response,
        val timeToBuy: Boolean? = null
    )

    data class VixIndexResponse(
        val code: Response,
        val vixIndex: VixIndex? = null
    ) {
        data class VixIndex(
            val currentPrice: Double,
            val openPrice: Double,
            val closePrice: Double,
            val marketStatus: String,
            val date: String
        )
    }
}
