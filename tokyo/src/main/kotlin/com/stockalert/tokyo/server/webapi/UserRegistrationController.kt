package com.stockalert.tokyo.server.webapi

import com.stockalert.tokyo.domain.port.user.RegisterUserPort
import com.stockalert.tokyo.domain.port.user.RegistrationCommand
import com.stockalert.tokyo.server.response.Response
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping("/registration")
class UserRegistrationController(
    private val registerUserPort: RegisterUserPort
) {
    @PostMapping
    fun register(@Valid @RequestBody request: UserRegistrationPayload): Response =
        registerUserPort.register(request.toCommand())
            .fold({
                when (it) {
                    RegisterUserPort.Error.DuplicateEmail  -> Response.badRequest()
                    RegisterUserPort.Error.DuplicateNickname  -> Response.badRequest()
                }
            }, {
                Response.success()
            })
}

data class UserRegistrationPayload(
    @Size(min = 2, max = 50, message = "Username must be between 2 and 50 characters")
    @NotNull
    val nickname: String,

    @Email(message = "Email address should  be valid")
    @Size(max = 100, message = "Email address must not be more than 100 characters")
    @NotNull
    val email: String,

    @Size(min = 6, max = 30, message = "Password must be between 6 and 30 characters")
    @NotNull
    val password: String
) {
    fun toCommand() = RegistrationCommand(
        nickname, email, password
    )
}