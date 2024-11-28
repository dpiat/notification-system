package com.dpiataikin.notificationservice.infrastructure.repository.impl

import com.dpiataikin.notificationservice.core.domain.Notification
import com.dpiataikin.notificationservice.core.repository.NotificationRepository
import com.dpiataikin.notificationservice.infrastructure.repository.NotificationMongoReactiveRepository
import reactor.core.publisher.Mono

class NotificationRepositoryImpl(
    private val notificationMongoReactiveRepository: NotificationMongoReactiveRepository
) : NotificationRepository {
    override fun send(notification: Notification): Mono<Void> {
        TODO("Not yet implemented")
    }
}