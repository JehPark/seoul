package com.stockalert.seoul.server.webapi

import com.stockalert.seoul.server.response.Response
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/stockAlert")
class StockAlertController(
    private val getCurrentVixIndex: GetCurrentVixIndex
) {
    companion object {
        private val DAY_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    }

    @GetMapping("/vix")
    fun getVixIndex(): VixIndexResponse {
        val vixIndex = getCurrentVixIndex()
        return VixIndexResponse(
            Response.success,
            VixIndexResponse.VixIndex(
                vixIndex,
                LocalDateTime.now().format(DAY_FORMATTER)
            )
        )
    }

    data class VixIndexResponse(
        val code: Response,
        val vixIndex: VixIndex
    ) {
        data class VixIndex(
            val value: Int,
            val date: String
        )
    }
}
