package com.stockalert.tokyo.server.configurations

import com.stockalert.tokyo.domain.adapter.RegisterUserAdapter
import com.stockalert.tokyo.domain.port.user.RegisterUserPort
import com.stockalert.tokyo.infra.repository.UserFinder
import com.stockalert.tokyo.domain.RegistrationManagement
import com.stockalert.tokyo.domain.common.security.PasswordEncryptor
import com.stockalert.tokyo.domain.common.security.PasswordEncryptorDelegator
import com.stockalert.tokyo.domain.event.DefaultDomainEventPublisher
import com.stockalert.tokyo.domain.event.DomainEventPublisher
import com.stockalert.tokyo.infra.mail.MailManager
import com.stockalert.tokyo.infra.mail.MailManagerImpl
import com.stockalert.tokyo.infra.repository.UserRegister
import dao.UserDao
import dao.jdbc.UserDaoImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Import(
    UserDaoImpl::class,
    DefaultDomainEventPublisher::class
)
@Configuration
class UserRegistrationConfiguration {
    @Bean
    fun registerUserPort(
        registrationManagement: RegistrationManagement,
        domainEventPublisher: DomainEventPublisher,
        mailManager: MailManager
    ): RegisterUserPort =
        RegisterUserAdapter(registrationManagement, domainEventPublisher, mailManager)

    @Bean
    fun registrationManagement(
        userFinder: UserFinder,
        userRegister: UserRegister,
        passwordEncryptor: PasswordEncryptor
    ) = RegistrationManagement(userFinder, userRegister, passwordEncryptor)

    @Bean
    fun passwordEncryptor() = PasswordEncryptorDelegator()

    @Bean
    fun userFinder(userDao: UserDao): UserFinder = UserFinder(userDao)

    @Bean
    fun userRegister(userDao: UserDao): UserRegister = UserRegister(userDao)

    @Bean
    fun mailManager(): MailManager = MailManagerImpl()
}