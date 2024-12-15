package com.dpiataikin.notificationservice.core.usecase.notification

import com.dpiataikin.notificationservice.core.domain.Contact
import com.dpiataikin.notificationservice.core.domain.Notification
import com.dpiataikin.notificationservice.core.usecase.UseCase
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class SendNotificationUseCase(
    private val contactRepository: ContactRepository,
    private val notificationRepository: NotificationRepository
) : UseCase<SendNotificationUseCase.Request, Mono<SendNotificationUseCase.Response>> {
    override fun execute(request: Request): Mono<Response> =
        contactRepository.findAllContactsByUserIdIn(request.notification.userTo)
            .collectList()
            .flatMap { notificationRepository.send(it, request.notification) }
            .then(Mono.empty())

    data class Request(
        val notification: Notification
    )

    class Response

    interface NotificationRepository {
        fun send(contacts: List<Contact>, notification: Notification): Mono<Void>
    }

    interface ContactRepository {
        fun findAllContactsByUserIdIn(userIds: List<String>): Flux<Contact>
    }
}