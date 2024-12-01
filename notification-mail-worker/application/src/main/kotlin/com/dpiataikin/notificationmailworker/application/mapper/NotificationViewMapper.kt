package com.dpiataikin.notificationmailworker.application.mapper

import com.dpiataikin.microservice.common.response.VoidResponse
import com.dpiataikin.notificationmailworker.core.domain.Mail
import com.dpiataikin.notificationmailworker.core.usecase.notification.SendMailUseCase
import com.dpiataikin.notificationservice.api.payload.request.CreateMailRequest
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface NotificationViewMapper {
    fun toMail(createMailRequest: CreateMailRequest): Mail
}

fun NotificationViewMapper.toSendNotificationUseCaseRequest(createMailRequest: CreateMailRequest): SendMailUseCase.Request =
    SendMailUseCase.Request(toMail(createMailRequest))

fun NotificationViewMapper.toVoidResponse(): VoidResponse {
    return VoidResponse.builder().build()
}