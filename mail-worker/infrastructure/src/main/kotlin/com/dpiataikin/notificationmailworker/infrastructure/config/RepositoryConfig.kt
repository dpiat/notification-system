package com.dpiataikin.notificationmailworker.infrastructure.config

import com.dpiataikin.notificationmailworker.infrastructure.mapper.NotificationLogMapper
import com.dpiataikin.notificationmailworker.infrastructure.mapper.NotificationTemplateMapper
import com.dpiataikin.notificationmailworker.infrastructure.repository.NotificationLogReactiveCrudRepository
import com.dpiataikin.notificationmailworker.infrastructure.repository.NotificationTemplateMongoReactiveRepository
import com.dpiataikin.notificationmailworker.infrastructure.repository.impl.MailRepositoryImpl
import com.dpiataikin.notificationmailworker.infrastructure.repository.impl.NotificationRepositoryImpl
import com.dpiataikin.notificationmailworker.infrastructure.repository.impl.NotificationTemplateRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender

@Configuration
open class RepositoryConfig {
    @Bean
    open fun mailRepository(
        javaMailSender: JavaMailSender
    ) = MailRepositoryImpl(
        javaMailSender
    )

    @Bean
    open fun notificationLogRepository(
        notificationLogReactiveCrudRepository: NotificationLogReactiveCrudRepository,
        notificationLogMapper: NotificationLogMapper
    ) = NotificationRepositoryImpl(
        notificationLogReactiveCrudRepository,
        notificationLogMapper
    )

    @Bean
    open fun notificationTemplateRepository(
        notificationTemplateMapper: NotificationTemplateMapper,
        notificationTemplateMongoReactiveRepository: NotificationTemplateMongoReactiveRepository
    ) = NotificationTemplateRepositoryImpl(
        notificationTemplateMapper,
        notificationTemplateMongoReactiveRepository
    )
}
