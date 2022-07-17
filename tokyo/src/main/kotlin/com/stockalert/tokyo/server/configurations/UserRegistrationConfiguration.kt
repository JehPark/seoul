package com.stockalert.tokyo.server.configurations

import com.stockalert.tokyo.domain.adapter.RegisterUserAdapter
import com.stockalert.tokyo.domain.port.user.RegisterUserPort
import com.stockalert.tokyo.domain.repository.UserFinder
import com.stockalert.tokyo.domain.repository.UserRegistrator
import dao.UserDao
import dao.jdbc.UserDaoImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Import(
    UserDaoImpl::class
)
@Configuration
class UserRegistrationConfiguration {
    @Bean
    fun registerUserPort(userFinder: UserFinder, userRegistrator: UserRegistrator): RegisterUserPort =
        RegisterUserAdapter(userFinder, userRegistrator)

    @Bean
    fun userFinder(userDao: UserDao): UserFinder = UserFinder(userDao)

    @Bean
    fun userRegistrator(userDao: UserDao): UserRegistrator = UserRegistrator(userDao)
}