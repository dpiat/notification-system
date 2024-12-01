package com.dpiataikin.notificationmailworker.infrastructure.repository.impl

import com.dpiataikin.notificationmailworker.core.domain.NotificationLog
import com.dpiataikin.notificationmailworker.core.repository.NotificationLogRepository
import com.dpiataikin.notificationmailworker.infrastructure.mapper.NotificationLogMapper
import com.dpiataikin.notificationmailworker.infrastructure.repository.NotificationLogReactiveCrudRepository
import reactor.core.publisher.Mono

class NotificationRepositoryImpl(
    private val notificationLogReactiveCrudRepository: NotificationLogReactiveCrudRepository,
    private val notificationLogMapper: NotificationLogMapper
) : NotificationLogRepository {
    override fun save(notificationLog: NotificationLog): Mono<Void> =
        Mono.just(notificationLog)
            .flatMap { Mono.just(notificationLogMapper.toNotificationLogEntity(it)) }
            .flatMap { notificationLogReactiveCrudRepository.save(it) }
            .then(Mono.empty())
}