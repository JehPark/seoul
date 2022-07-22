package com.stockalert.tokyo.domain.port.user

import arrow.core.Either

interface RegisterUserPort {
    fun register(registrationCommand: RegistrationCommand): Either<Error, Long>

    sealed class Error {
        object DuplicateEmail : Error()
        object DuplicateNickname : Error()
    }
}

class RegistrationCommand(
    val nickname: String,
    val email: String,
    val password: String
)