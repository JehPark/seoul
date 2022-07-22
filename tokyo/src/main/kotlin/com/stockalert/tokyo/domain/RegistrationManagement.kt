package com.stockalert.tokyo.domain

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.stockalert.tokyo.domain.common.security.PasswordEncryptor
import com.stockalert.tokyo.domain.port.user.RegisterUserPort
import com.stockalert.tokyo.domain.port.user.RegistrationCommand
import com.stockalert.tokyo.infra.mail.MailManager
import com.stockalert.tokyo.infra.repository.UserFinder
import com.stockalert.tokyo.infra.repository.UserRegister
import model.entity.UserEntity
import java.util.Date

class RegistrationManagement(
    private val userFinder: UserFinder,
    private val userRegister: UserRegister,
    private val passwordEncryptor: PasswordEncryptor,
) {
    operator fun invoke(nickname: String, email: String, password: String, signedUpAt: Date): Either<RegisterUserPort.Error, UserEntity> {
        val verificationResult = verifyUserRegistration(nickname, email)
        if (verificationResult != null) return verificationResult.left()
        val encryptedPassword = passwordEncryptor.encrypt(password)
        return userRegister.register(nickname, encryptedPassword, email, signedUpAt).right()
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