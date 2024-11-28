package com.dpiataikin.notificationservice.core.usecase.notification

import com.dpiataikin.notificationservice.core.domain.Notification
import com.dpiataikin.notificationservice.core.usecase.UseCase
import reactor.core.publisher.Mono

class SendNotificationUseCase(
    private val notificationRepository: NotificationRepository
) : UseCase<SendNotificationUseCase.Request, Mono<SendNotificationUseCase.Response>> {
    override fun execute(request: Request): Mono<Response> {
        return notificationRepository.send(request.notification).then(Mono.empty())
    }

    data class Request(
        val notification: Notification
    )

    class Response

    interface NotificationRepository {
        fun send(notification: Notification): Mono<Void>
    }
}