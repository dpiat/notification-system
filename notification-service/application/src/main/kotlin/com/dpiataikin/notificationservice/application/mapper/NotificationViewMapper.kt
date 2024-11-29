package com.dpiataikin.notificationservice.application.mapper

import com.dpiataikin.microservice.common.response.VoidResponse
import com.dpiataikin.notificationservice.api.payload.request.CreateNotificationRequest
import com.dpiataikin.notificationservice.core.domain.Notification
import com.dpiataikin.notificationservice.core.usecase.notification.SendNotificationUseCase
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface NotificationViewMapper {
    fun toNotification(createNotificationRequest: CreateNotificationRequest): Notification
}

fun NotificationViewMapper.toSendNotificationUseCaseRequest(createNotificationRequest: CreateNotificationRequest): SendNotificationUseCase.Request =
    SendNotificationUseCase.Request(toNotification(createNotificationRequest))

fun NotificationViewMapper.toVoidResponse(): VoidResponse {
    return VoidResponse.builder().build()
}