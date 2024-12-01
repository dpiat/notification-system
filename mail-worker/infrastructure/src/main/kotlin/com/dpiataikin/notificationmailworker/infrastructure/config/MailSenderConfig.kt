package com.dpiataikin.notificationmailworker.infrastructure.config

import org.apache.kafka.common.config.types.Password
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl
import java.util.*


@Configuration
open class MailSenderConfig {
    @Bean
    open fun javaMailSender(
        @Value("\${mailsender.host}") host: String,
        @Value("\${mailsender.port}") port: Int,
        @Value("\${mailsender.username}") userName: String,
        @Value("\${mailsender.password}") password: String
    ): JavaMailSender {
        val mailSender = JavaMailSenderImpl()
        mailSender.host = host
        mailSender.port = port
        mailSender.username = userName
        mailSender.password = password

        val props: Properties = mailSender.javaMailProperties
        props.put("mail.transport.protocol", "smtp")
        props.put("mail.smtp.auth", "true")
        props.put("mail.smtp.starttls.enable", "true")
        props.put("mail.debug", "true")

        return mailSender
    }
}