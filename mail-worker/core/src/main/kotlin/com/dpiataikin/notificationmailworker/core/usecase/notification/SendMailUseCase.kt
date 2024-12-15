package com.dpiataikin.notificationmailworker.core.usecase.notification

import com.dpiataikin.notificationmailworker.core.domain.Mail
import com.dpiataikin.notificationmailworker.core.domain.NotificationLog
import com.dpiataikin.notificationmailworker.core.domain.NotificationLogStatus
import com.dpiataikin.notificationmailworker.core.exception.NotificationHandledException
import com.dpiataikin.notificationmailworker.core.usecase.UseCase
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.doOnError
import java.util.UUID

class SendMailUseCase(
    private val notificationLogRepository: NotificationLogRepository,
    private val mailRepository: MailRepository
) : UseCase<SendMailUseCase.Request, Mono<SendMailUseCase.Response>> {
    override fun execute(request: Request): Mono<Response> =
        notificationLogRepository.save(
                NotificationLog(
                    UUID.randomUUID().toString(),
                    request.mail.id,
                    NotificationLogStatus.HANDLED_BY_WORKER
                )
            )
            .flatMap { mailRepository.send(request.mail).thenReturn(it) }
            .flatMap { Mono.just(it.apply { it.status = NotificationLogStatus.SENT }) }
            .flatMap { notificationLogRepository.save(it) }
            .onErrorResume(NotificationHandledException::class.java) {
                print("${it.message}")
                Mono.empty()
            }
            .then(Mono.empty())

    class Request(
        val mail: Mail
    )

    class Response

    interface NotificationLogRepository {
        fun save(notificationLog: NotificationLog): Mono<NotificationLog>
    }

    interface MailRepository {
        fun send(mail: Mail): Mono<Void>
    }
}