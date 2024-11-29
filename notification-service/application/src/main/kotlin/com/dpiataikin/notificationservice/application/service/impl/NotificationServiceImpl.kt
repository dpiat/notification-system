package com.dpiataikin.notificationservice.application.service.impl

import com.dpiataikin.microservice.common.response.VoidResponse
import com.dpiataikin.notificationservice.api.payload.request.CreateNotificationRequest
import com.dpiataikin.notificationservice.application.mapper.NotificationViewMapper
import com.dpiataikin.notificationservice.application.mapper.toSendNotificationUseCaseRequest
import com.dpiataikin.notificationservice.application.mapper.toVoidResponse
import com.dpiataikin.notificationservice.application.service.NotificationService
import com.dpiataikin.notificationservice.core.usecase.UseCaseExecutor
import com.dpiataikin.notificationservice.core.usecase.notification.SendNotificationUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class NotificationServiceImpl(
    private val notificationViewMapper: NotificationViewMapper,
    private val useCaseExecutor: UseCaseExecutor,
    private val sendNotificationUseCase: SendNotificationUseCase
): NotificationService {
    override fun createNotification(createNotificationRequest: CreateNotificationRequest): Mono<VoidResponse> =
        useCaseExecutor.invoke(
            useCase = sendNotificationUseCase,
            requestDto = createNotificationRequest,
            requestConverter = { notificationViewMapper.toSendNotificationUseCaseRequest(it) },
            responseConverter = convertMonoToMono { notificationViewMapper.toVoidResponse() }
        )

    fun <T, R : Any> convertMonoToMono(mapperFunction: (T) -> R): (Mono<T>) -> Mono<R> = { mono ->
        mono.flatMap { item ->
            Mono.just(mapperFunction(item))
        }
    }
}
