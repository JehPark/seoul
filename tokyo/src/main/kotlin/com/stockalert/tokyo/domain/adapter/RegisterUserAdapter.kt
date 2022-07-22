package com.stockalert.tokyo.domain.adapter

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.stockalert.tokyo.domain.RegistrationManagement
import com.stockalert.tokyo.domain.event.DomainEventPublisher
import com.stockalert.tokyo.domain.event.user.UserRegisteredEvent
import com.stockalert.tokyo.infra.mail.MailManager
import com.stockalert.tokyo.domain.port.user.RegisterUserPort
import com.stockalert.tokyo.domain.port.user.RegistrationCommand
import com.stockalert.tokyo.infra.repository.UserFinder
import model.entity.UserEntity
import java.time.Instant
import java.util.Date

class RegisterUserAdapter(
    private val registrationManagement: RegistrationManagement,
    private val domainEventPublisher: DomainEventPublisher,
    private val mailManager: MailManager
) : RegisterUserPort {
    override fun register(registrationCommand: RegistrationCommand): Either<RegisterUserPort.Error, Long> {
        return registrationCommand.let {
            registrationManagement(it.nickname, it.email, it.password, Date.from(Instant.now()))
        }.fold({
            it.left()
        }, {
            sendWelcomeMessage(it)
            domainEventPublisher.publish(UserRegisteredEvent(it))
            it.id.right()
        })
    }

    private fun sendWelcomeMessage(user: UserEntity) {
        mailManager.send(
            user.email,
            user.nickname,
            "Welcome to Stock Alert System",
            "Hello, ${user.nickname}. We are really happy to meet you as a customer"
        )
    }
}