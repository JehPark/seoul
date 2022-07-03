package com.stockalert.seoul.retrofit

import retrofit2.Response
import java.io.IOException

fun <T> Response<T>.getBody(): T =
    when {
        isSuccessful -> body()!!
        else -> throw IOException("HTTP Status ${code()}")
    }
