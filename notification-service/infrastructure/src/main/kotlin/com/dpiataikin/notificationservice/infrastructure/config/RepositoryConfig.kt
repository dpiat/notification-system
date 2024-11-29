package com.dpiataikin.notificationservice.infrastructure.config

import com.dpiataikin.microservice.common.event.starter.publisher.EventPublisher
import com.dpiataikin.notificationservice.core.repository.ContactRepository
import com.dpiataikin.notificationservice.core.repository.NotificationRepository
import com.dpiataikin.notificationservice.infrastructure.mapper.ContactDatabaseMapper
import com.dpiataikin.notificationservice.infrastructure.mapper.NotificationEventMapper
import com.dpiataikin.notificationservice.infrastructure.repository.ContactMongoReactiveRepository
import com.dpiataikin.notificationservice.infrastructure.repository.NotificationMongoReactiveRepository
import com.dpiataikin.notificationservice.infrastructure.repository.impl.ContactRepositoryImpl
import com.dpiataikin.notificationservice.infrastructure.repository.impl.NotificationRepositoryImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class RepositoryConfig {
    @Bean
    open fun contactRepository(
        contactMongoReactiveRepository: ContactMongoReactiveRepository,
        contactDatabaseMapper: ContactDatabaseMapper
    ): ContactRepository {
        return ContactRepositoryImpl(contactMongoReactiveRepository, contactDatabaseMapper)
    }

    @Bean
    open fun notificationRepository(
        contactMongoReactiveRepository: ContactMongoReactiveRepository,
        notificationEventMapper: NotificationEventMapper,
        eventPublisher: EventPublisher
    ): NotificationRepository {
        return NotificationRepositoryImpl(
            contactMongoReactiveRepository,
            notificationEventMapper,
            eventPublisher
        )
    }
}