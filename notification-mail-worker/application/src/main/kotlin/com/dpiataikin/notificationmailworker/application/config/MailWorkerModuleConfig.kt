package com.dpiataikin.notificationmailworker.application.config

import com.dpiataikin.notificationmailworker.core.repository.MailRepository
import com.dpiataikin.notificationmailworker.core.repository.NotificationLogRepository
import com.dpiataikin.notificationmailworker.core.usecase.UseCaseExecutorImp
import com.dpiataikin.notificationmailworker.core.usecase.notification.SendMailUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MailWorkerModuleConfig {
    @Bean
    fun useCaseExecutor() = UseCaseExecutorImp()

    @Bean
    fun getSendMailUseCase(
        notificationRepository: NotificationLogRepository,
        mailRepository: MailRepository
    ) = SendMailUseCase(notificationRepository, mailRepository)
}
