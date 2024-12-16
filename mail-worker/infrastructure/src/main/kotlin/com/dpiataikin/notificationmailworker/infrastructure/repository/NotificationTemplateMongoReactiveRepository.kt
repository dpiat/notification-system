package com.dpiataikin.notificationmailworker.infrastructure.repository

import com.dpiataikin.notificationmailworker.infrastructure.domain.NotificationTemplateEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Mono

interface NotificationTemplateMongoReactiveRepository: ReactiveMongoRepository<NotificationTemplateEntity, String> {
    fun findFirstBy(): Mono<NotificationTemplateEntity>
}