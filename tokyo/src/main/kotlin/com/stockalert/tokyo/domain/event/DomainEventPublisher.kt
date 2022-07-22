package com.stockalert.tokyo.domain.event

interface DomainEventPublisher {
    fun publish(event: DomainEvent)
}