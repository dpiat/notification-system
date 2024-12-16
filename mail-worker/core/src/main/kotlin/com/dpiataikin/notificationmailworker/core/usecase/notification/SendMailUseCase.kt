package com.dpiataikin.notificationmailworker.core.usecase.notification

import com.dpiataikin.notificationmailworker.core.domain.Mail
import com.dpiataikin.notificationmailworker.core.domain.NotificationLog
import com.dpiataikin.notificationmailworker.core.domain.NotificationLogStatus
import com.dpiataikin.notificationmailworker.core.domain.NotificationTemplate
import com.dpiataikin.notificationmailworker.core.exception.NotificationHandledException
import com.dpiataikin.notificationmailworker.core.usecase.UseCase
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import reactor.core.publisher.Mono
import reactor.kotlin.core.util.function.component1
import reactor.kotlin.core.util.function.component2
import java.util.*

class SendMailUseCase(
    private val notificationLogRepository: NotificationLogRepository,
    private val mailRepository: MailRepository,
    private val notificationTemplateRepository: NotificationTemplateRepository
) : UseCase<SendMailUseCase.Request, Mono<SendMailUseCase.Response>> {

    val LOG: Logger = LogManager.getLogger(SendMailUseCase::class.java)

    override fun execute(request: Request): Mono<Response> =
        notificationLogRepository.save(
            NotificationLog(
                UUID.randomUUID().toString(),
                request.mail.id,
                NotificationLogStatus.HANDLED_BY_WORKER
            )
        )
            .flatMap { notificationLog ->
                notificationTemplateRepository.findLatest()
                    .flatMap {
                        notificationTemplateRepository.renderNotification(
                            it.value,
                            mapOf("body" to request.mail.body)
                        )
                    }
                    .flatMap { Mono.zip(Mono.just(it), Mono.just(notificationLog)) }
            }
            .flatMap { (renderedTemplate: String, notificationLog: NotificationLog) ->
                mailRepository.send(
                    request.apply { mail.body = renderedTemplate }.mail
                )
                .thenReturn(notificationLog)
            }
            .flatMap { Mono.just(it.apply { it.status = NotificationLogStatus.SENT }) }
            .flatMap { notificationLogRepository.save(it) }
            .onErrorResume(NotificationHandledException::class.java) {
                LOG.info("${it.message}")
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

    interface NotificationTemplateRepository {
        fun findLatest(): Mono<NotificationTemplate>
        fun renderNotification(htmlTemplate: String, variables: Map<String, Any>): Mono<String>
    }
}