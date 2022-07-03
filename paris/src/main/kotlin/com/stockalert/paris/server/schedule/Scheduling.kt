package com.stockalert.paris.server.schedule

import com.stockalert.seoul.vixindex.usecase.GetCurrentVixIndexUsage

class Scheduling(
    private val getCurrentVixIndexUsage: GetCurrentVixIndexUsage
) {
}