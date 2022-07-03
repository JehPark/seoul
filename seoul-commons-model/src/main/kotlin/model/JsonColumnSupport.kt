package model

import com.fasterxml.jackson.core.type.TypeReference

interface JsonColumnSupport<T> {
    fun getV(): String?

    fun setV(v: String?)

    fun getJsonColumn(): T?

    fun setJsonColumn(JsonColumns: T?)

    fun getType(): TypeReference<T>
}