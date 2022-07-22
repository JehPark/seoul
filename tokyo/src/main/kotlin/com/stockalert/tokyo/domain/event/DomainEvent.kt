package com.stockalert.tokyo.domain.event

import org.springframework.context.ApplicationEvent

abstract class DomainEvent(
    source: Any
) : ApplicationEvent(source) {
    fun occuredAt() = timestamp
}