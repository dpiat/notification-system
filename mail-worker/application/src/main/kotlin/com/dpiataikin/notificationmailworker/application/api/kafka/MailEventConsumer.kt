package com.dpiataikin.notificationmailworker.application.api.kafka

import com.dpiataikin.microservice.common.event.starter.handler.DomainEventHandlers
import com.dpiataikin.microservice.common.event.starter.handler.DomainEventHandlersBuilder
import com.dpiataikin.microservice.common.event.starter.registry.DomainRegistry
import com.dpiataikin.notificationmailworker.application.service.MailService
import com.dpiataikin.notificationservice.api.event.NotificationDomain
import com.dpiataikin.notificationservice.api.event.notification.EmailNotification
import com.dpiataikin.notificationservice.api.payload.request.CreateMailRequest
import org.springframework.stereotype.Service

@Service
class MailEventConsumer(
    private val domainRegistry: DomainRegistry,
    private val mailService: MailService
) {
    fun eventHandlers(): DomainEventHandlers {
        return DomainEventHandlersBuilder
            .withRegistry(domainRegistry)
            .forDomain(NotificationDomain.NOTIFICATION.domainName)
            .onEvent(EmailNotification::class.java, this::handleEmailNotification)
            .build()
    }

    fun handleEmailNotification(emailNotification: EmailNotification) {
        val createMailRequest = CreateMailRequest(
            id = emailNotification.id,
            userFrom = emailNotification.userFrom,
            userTo = emailNotification.userTo,
            subject = emailNotification.subject,
            body = emailNotification.body
        )
        mailService.sendMail(createMailRequest).subscribe()
    }
}