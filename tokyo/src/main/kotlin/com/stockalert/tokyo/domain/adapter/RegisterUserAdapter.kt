package com.stockalert.tokyo.domain.adapter

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.stockalert.tokyo.domain.port.user.RegisterUserPort
import com.stockalert.tokyo.domain.repository.UserFinder
import com.stockalert.tokyo.domain.repository.UserRegistrator
import model.entity.UserEntity
import java.time.Instant
import java.util.Date

class RegisterUserAdapter(
    private val userFinder: UserFinder,
    private val userRegistrator: UserRegistrator
) : RegisterUserPort {
    override fun registerUser(nickname: String, email: String, password: String): Either<RegisterUserPort.Error, Int> {
        return when (val result = verifyUserRegistration(nickname, email)) {
            is RegisterUserPort.Error -> result.left()
            null -> return userRegistrator.register(nickname, email, password, Date.from(Instant.now())).right()
        }
    }

    private fun verifyUserRegistration(nickname: String, email: String): RegisterUserPort.Error? {
        if (userFinder.byEmail(email) != null) {
            return RegisterUserPort.Error.DuplicateEmail
        }
        if (userFinder.byNickname(nickname) != null) {
            return RegisterUserPort.Error.DuplicateNickname
        }
        return null
    }
}