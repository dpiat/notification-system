package com.dpiataikin.notificationmailworker.infrastructure.mapper

import com.dpiataikin.notificationmailworker.core.domain.NotificationLog
import com.dpiataikin.notificationmailworker.infrastructure.domain.NotificationLogEntity
import org.mapstruct.*


@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface NotificationLogMapper {
    @Mapping(target = "neww", expression = "java(true)")
    fun toNotificationLogEntity(notificationLog: NotificationLog): NotificationLogEntity
    fun updateNotificationLog(@MappingTarget notificationLogEntity: NotificationLogEntity, notificationLog: NotificationLog): NotificationLogEntity
}
