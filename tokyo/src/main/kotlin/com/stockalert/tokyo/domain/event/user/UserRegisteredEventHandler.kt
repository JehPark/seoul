package com.stockalert.tokyo.domain.event.user

import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UserRegisteredEventHandler {
    private val logger = LoggerFactory.getLogger(javaClass)

    @EventListener(UserRegisteredEvent::class)
    fun handleEvent(event: UserRegisteredEvent) {
        logger.debug("Handling ${event.user.email} registration event")
    }
}