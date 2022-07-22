package com.stockalert.tokyo.domain.event.user

import com.stockalert.tokyo.domain.event.DomainEvent
import model.entity.UserEntity

data class UserRegisteredEvent(
    val user: UserEntity
): DomainEvent(user)