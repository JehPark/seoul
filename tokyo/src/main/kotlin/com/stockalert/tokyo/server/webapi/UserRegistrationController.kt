package com.stockalert.tokyo.server.webapi

import com.stockalert.tokyo.domain.port.user.RegisterUserPort
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/registration")
class UserRegistrationController(
    private val registerUserPort: RegisterUserPort
) {
    @PostMapping
    fun registerUser(@RequestBody request: Request) {
        registerUserPort.registerUser(request.nickname, request.email, request.password)
    }
}

data class Request(
    val nickname: String,
    val email: String,
    val password: String
)