package com.stockalert.tokyo.server.response

import com.fasterxml.jackson.annotation.JsonValue

class Response(
    val status : Status
) {
    data class Status(@JsonValue val code: Int) {
        companion object {
            val SUCESS = Status(0)
            val BAD_REQUEST = Status(-400)
            val NOT_FOUND = Status(-404)
            val ERROR = Status(-500)
        }
    }

    companion object {
        fun error() = Response(Status.ERROR)
        fun success() = Response(Status.SUCESS)
        fun badRequest() = Response(Status.BAD_REQUEST)
        fun notFound() = Response(Status.NOT_FOUND)
    }
}