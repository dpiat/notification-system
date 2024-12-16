package com.dpiataikin.notificationmailworker.infrastructure.mapper

import com.dpiataikin.notificationmailworker.core.domain.NotificationTemplate
import com.dpiataikin.notificationmailworker.infrastructure.domain.NotificationTemplateEntity
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy


@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface NotificationTemplateMapper {
    fun toNotificationTemplate(notificationTemplateEntity: NotificationTemplateEntity): NotificationTemplate
}
