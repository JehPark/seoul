package com.stockalert.paris.server.response

import com.fasterxml.jackson.annotation.JsonValue

class Response (val status: Status){
    data class Status(@JsonValue val code: Int) {
        companion object {
            val SUCCESS = Status(0)
            val BAD_REQUEST = Status(-400)
            val NOT_FOUND = Status(-404)
            val ERROR = Status(-500)
        }
    }

    companion object {
        val error = Response(Status.ERROR)
        val success = Response(Status.SUCCESS)
    }
}