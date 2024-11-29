package com.dpiataikin.notificationservice.infrastructure.repository.impl

import com.dpiataikin.microservice.common.event.starter.publisher.EventPublisher
import com.dpiataikin.notificationservice.api.event.NotificationDomain
import com.dpiataikin.notificationservice.core.domain.Notification
import com.dpiataikin.notificationservice.core.repository.NotificationRepository
import com.dpiataikin.notificationservice.infrastructure.mapper.NotificationEventMapper
import com.dpiataikin.notificationservice.infrastructure.mapper.toDomainEvent
import com.dpiataikin.notificationservice.infrastructure.repository.ContactMongoReactiveRepository
import reactor.core.publisher.Mono

class NotificationRepositoryImpl(
    private val contactMongoReactiveRepository: ContactMongoReactiveRepository,
    private val notificationEventMapper: NotificationEventMapper,
    private val eventPublisher: EventPublisher
) : NotificationRepository {
    override fun send(notification: Notification): Mono<Void> {
        return contactMongoReactiveRepository.findAllByUserIdIn(notification.userTo)
            .collectList()
            .flatMap {
                Mono.just(
                    it.stream()
                        .map { notificationEventMapper.toDomainEvent(it, notification) }
                        .toList()
                )
            }
            .doOnSuccess { eventPublisher.publish(NotificationDomain.NOTIFICATION.domainName, it) }
            .then()
    }
}