package com.dpiataikin.notificationmailworker.infrastructure.repository.impl

import com.dpiataikin.notificationmailworker.core.domain.NotificationLog
import com.dpiataikin.notificationmailworker.core.exception.NotificationHandledException
import com.dpiataikin.notificationmailworker.core.repository.NotificationLogRepository
import com.dpiataikin.notificationmailworker.infrastructure.mapper.NotificationLogMapper
import com.dpiataikin.notificationmailworker.infrastructure.repository.NotificationLogReactiveCrudRepository
import org.springframework.dao.DuplicateKeyException
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.doOnError

class NotificationRepositoryImpl(
    private val notificationLogReactiveCrudRepository: NotificationLogReactiveCrudRepository,
    private val notificationLogMapper: NotificationLogMapper
) : NotificationLogRepository {
    override fun save(notificationLog: NotificationLog): Mono<NotificationLog> =
        notificationLogReactiveCrudRepository.findById(notificationLog.id)
            .defaultIfEmpty(notificationLogMapper.toNotificationLogEntity(notificationLog))
            .flatMap { Mono.just(notificationLogMapper.updateNotificationLog(it, notificationLog)) }
            .flatMap { notificationLogReactiveCrudRepository.save(it) }
            .onErrorMap(DuplicateKeyException::class.java) {
                NotificationHandledException()
            }
            .flatMap { Mono.just(notificationLog) }
}