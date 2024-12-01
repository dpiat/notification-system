package com.dpiataikin.notificationmailworker.infrastructure.mapper

import com.dpiataikin.notificationmailworker.core.domain.NotificationLog
import com.dpiataikin.notificationmailworker.infrastructure.domain.NotificationLogEntity
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy


@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface NotificationLogMapper {
    fun toNotificationLogEntity(notificationLog: NotificationLog): NotificationLogEntity
}
