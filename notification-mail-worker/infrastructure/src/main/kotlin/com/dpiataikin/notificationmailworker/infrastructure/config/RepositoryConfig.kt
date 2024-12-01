package com.dpiataikin.notificationmailworker.infrastructure.config

import com.dpiataikin.notificationmailworker.core.repository.MailRepository
import com.dpiataikin.notificationmailworker.core.repository.NotificationLogRepository
import com.dpiataikin.notificationmailworker.infrastructure.mapper.NotificationLogMapper
import com.dpiataikin.notificationmailworker.infrastructure.repository.NotificationLogReactiveCrudRepository
import com.dpiataikin.notificationmailworker.infrastructure.repository.impl.MailRepositoryImpl
import com.dpiataikin.notificationmailworker.infrastructure.repository.impl.NotificationRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender

@Configuration
open class RepositoryConfig {
    @Bean
    open fun mailRepository(
        javaMailSender: JavaMailSender
    ): MailRepository {
        return MailRepositoryImpl(javaMailSender)
    }

    @Bean
    open fun notificationLogRepository(
        notificationLogReactiveCrudRepository: NotificationLogReactiveCrudRepository,
        notificationLogMapper: NotificationLogMapper
    ): NotificationLogRepository {
        return NotificationRepositoryImpl(
            notificationLogReactiveCrudRepository,
            notificationLogMapper
        )
    }
}