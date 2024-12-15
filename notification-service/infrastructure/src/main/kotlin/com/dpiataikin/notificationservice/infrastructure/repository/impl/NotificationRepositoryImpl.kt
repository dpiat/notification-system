package com.dpiataikin.notificationservice.infrastructure.repository.impl

import com.dpiataikin.microservice.common.event.starter.publisher.EventPublisher
import com.dpiataikin.notificationservice.api.event.NotificationDomain
import com.dpiataikin.notificationservice.core.domain.Contact
import com.dpiataikin.notificationservice.core.domain.Notification
import com.dpiataikin.notificationservice.core.repository.NotificationRepository
import com.dpiataikin.notificationservice.infrastructure.mapper.NotificationEventMapper
import com.dpiataikin.notificationservice.infrastructure.mapper.toDomainEvent
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class NotificationRepositoryImpl(
    private val notificationEventMapper: NotificationEventMapper,
    private val eventPublisher: EventPublisher
) : NotificationRepository {
    override fun send(contacts: List<Contact>, notification: Notification): Mono<Void> =
        Flux.fromIterable(contacts)
            .map { notificationEventMapper.toDomainEvent(it, notification) }
            .collectList()
            .doOnSuccess { eventPublisher.publish(NotificationDomain.NOTIFICATION.domainName, it) }
            .then()
}
