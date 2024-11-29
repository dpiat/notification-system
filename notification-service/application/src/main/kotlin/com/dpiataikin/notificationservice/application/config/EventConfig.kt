package com.dpiataikin.notificationservice.application.config

import com.dpiataikin.microservice.common.event.starter.DomainRelation
import com.dpiataikin.microservice.common.event.starter.registry.DomainRegistry
import com.dpiataikin.microservice.common.event.starter.registry.builder.DomainRegistryBuilder
import com.dpiataikin.notificationservice.api.event.NotificationDomain
import com.dpiataikin.notificationservice.api.event.NotificationEventType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Set

@Configuration
open class EventConfig {
    @Bean
    open fun domainRegistry(): DomainRegistry {
        return DomainRegistryBuilder()
            .register(
                NotificationDomain.NOTIFICATION.domainName,
                NotificationEventType.entries.toTypedArray(),
                Set.of(DomainRelation.PRODUCER)
            )
            .build()
    }
}