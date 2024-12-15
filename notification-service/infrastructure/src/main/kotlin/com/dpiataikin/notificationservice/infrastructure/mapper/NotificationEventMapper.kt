package com.dpiataikin.notificationservice.infrastructure.mapper

import com.dpiataikin.microservice.common.event.starter.model.DomainEvent
import com.dpiataikin.notificationservice.api.event.notification.EmailNotification
import com.dpiataikin.notificationservice.core.domain.Contact
import com.dpiataikin.notificationservice.core.domain.ContactType
import com.dpiataikin.notificationservice.core.domain.Notification
import com.dpiataikin.notificationservice.infrastructure.domain.ContactEntity
import org.apache.kafka.common.Uuid
import org.apache.kafka.common.protocol.types.Field.UUID
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy


@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface NotificationEventMapper

fun NotificationEventMapper.toDomainEvent(contact: Contact, notification: Notification): DomainEvent {
    return when (contact.type) {
        ContactType.EMAIL -> EmailNotification(
            id = Uuid.randomUuid().toString(),
            userFrom = notification.userFrom,
            userTo = contact.value,
            subject = notification.subject,
            body = notification.body
        )
    }
}
