package com.dpiataikin.notificationmailworker.application.config

import com.dpiataikin.microservice.common.event.starter.DomainRelation
import com.dpiataikin.microservice.common.event.starter.handler.DomainEventHandlers
import com.dpiataikin.microservice.common.event.starter.registry.DomainRegistry
import com.dpiataikin.microservice.common.event.starter.registry.builder.DomainRegistryBuilder
import com.dpiataikin.notificationmailworker.application.api.kafka.MailEventConsumer
import com.dpiataikin.notificationservice.api.event.NotificationDomain
import com.dpiataikin.notificationservice.api.event.NotificationEventType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Set

@Configuration
class EventConfig {
    @Bean
    fun domainRegistry(): DomainRegistry {
        return DomainRegistryBuilder()
            .register(
                NotificationDomain.NOTIFICATION.domainName,
                NotificationEventType.entries.toTypedArray(),
                Set.of(DomainRelation.CONSUMER)
            )
            .build()
    }

    @Bean
    fun domainEventHandlers(eventConsumer: MailEventConsumer): DomainEventHandlers {
        return eventConsumer.eventHandlers()
    }
}
