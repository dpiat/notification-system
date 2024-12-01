package com.dpiataikin.notificationmailworker.core.usecase.notification

import com.dpiataikin.notificationmailworker.core.domain.Mail
import com.dpiataikin.notificationmailworker.core.domain.NotificationLog
import com.dpiataikin.notificationmailworker.core.usecase.UseCase
import reactor.core.publisher.Mono

class SendMailUseCase(
    private val notificationLogRepository: NotificationLogRepository,
    private val mailRepository: MailRepository
) : UseCase<SendMailUseCase.Request, Mono<SendMailUseCase.Response>> {
    override fun execute(request: Request): Mono<Response> =
        notificationLogRepository.save(NotificationLog(request.mail.id))
            .then(mailRepository.send(request.mail))
            .then(Mono.empty())

    data class Request(
        val mail: Mail
    )

    class Response

    interface NotificationLogRepository {
        fun save(notificationLog: NotificationLog): Mono<Void>
    }

    interface MailRepository {
        fun send(mail: Mail): Mono<Void>
    }
}