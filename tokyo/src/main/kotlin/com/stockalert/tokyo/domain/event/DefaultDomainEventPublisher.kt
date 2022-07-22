package com.stockalert.tokyo.domain.event

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class DefaultDomainEventPublisher(
    @Autowired private val actualPublisher: ApplicationEventPublisher
): DomainEventPublisher {
    override fun publish(event: DomainEvent) {
        actualPublisher.publishEvent(event)
    }
}