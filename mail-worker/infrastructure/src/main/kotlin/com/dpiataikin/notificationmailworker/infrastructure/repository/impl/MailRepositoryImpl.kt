package com.dpiataikin.notificationmailworker.infrastructure.repository.impl

import com.dpiataikin.notificationmailworker.core.domain.Mail
import com.dpiataikin.notificationmailworker.core.repository.MailRepository
import jakarta.mail.internet.MimeMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import reactor.core.publisher.Mono


class MailRepositoryImpl(
    private val javaMailSender: JavaMailSender,
) : MailRepository {
    override fun send(mail: Mail): Mono<Void> {
        val message: MimeMessage = javaMailSender.createMimeMessage()
        val helper = MimeMessageHelper(message, true, "UTF-8").apply {
            setFrom(mail.userFrom)
            setTo(mail.userTo)
            setSubject(mail.subject)
            setText(mail.body, true)
        }
        return Mono
            .fromCallable { javaMailSender.send(message) }
            .then(Mono.empty())
    }
}