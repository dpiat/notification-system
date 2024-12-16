package com.dpiataikin.notificationmailworker.infrastructure.repository.impl

import com.dpiataikin.notificationmailworker.core.domain.NotificationTemplate
import com.dpiataikin.notificationmailworker.core.repository.NotificationTemplateRepository
import com.dpiataikin.notificationmailworker.infrastructure.mapper.NotificationTemplateMapper
import com.dpiataikin.notificationmailworker.infrastructure.repository.NotificationTemplateMongoReactiveRepository
import reactor.core.publisher.Mono

class NotificationTemplateRepositoryImpl(
    private val notificationTemplateMapper: NotificationTemplateMapper,
    private val notificationTemplateMongoReactiveRepository: NotificationTemplateMongoReactiveRepository
): NotificationTemplateRepository {
    override fun findLatest(): Mono<NotificationTemplate> =
        notificationTemplateMongoReactiveRepository.findFirstBy()
            .flatMap { Mono.just(notificationTemplateMapper.toNotificationTemplate(it)) }

    override fun renderNotification(htmlTemplate: String, variables: Map<String, Any>): Mono<String> =
        Mono.just(htmlTemplate.let {
            val body: String = variables["body"] as String
            it.replace("\${body}", body)
        })
}
