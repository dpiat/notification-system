package com.dpiataikin.notificationmailworker.infrastructure.repository.impl

import com.dpiataikin.notificationmailworker.core.domain.Mail
import com.dpiataikin.notificationmailworker.core.repository.MailRepository
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import reactor.core.publisher.Mono

class MailRepositoryImpl(
    private val javaMailSender: JavaMailSender,
) : MailRepository {
    override fun send(mail: Mail): Mono<Void> {
        val message = SimpleMailMessage().apply {
            from = mail.userFrom
            setTo(mail.userTo)
            subject = mail.subject
            text = mail.body
        }
        return Mono
            .fromCallable { javaMailSender.send(message) }
            .then(Mono.empty())
    }
}