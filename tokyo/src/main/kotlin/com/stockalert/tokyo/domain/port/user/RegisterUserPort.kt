package com.stockalert.tokyo.domain.port.user

import arrow.core.Either

interface RegisterUserPort {
    fun registerUser(nickname: String, email: String, password: String): Either<Error, Int>

    sealed class Error {
        object DuplicateEmail : Error()
        object DuplicateNickname : Error()
    }
}