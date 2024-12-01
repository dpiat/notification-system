package com.dpiataikin.notificationmailworker.application.service.impl

import com.dpiataikin.microservice.common.response.VoidResponse
import com.dpiataikin.notificationservice.api.payload.request.CreateMailRequest
import com.dpiataikin.notificationmailworker.application.mapper.NotificationViewMapper
import com.dpiataikin.notificationmailworker.application.mapper.toSendNotificationUseCaseRequest
import com.dpiataikin.notificationmailworker.application.mapper.toVoidResponse
import com.dpiataikin.notificationmailworker.application.service.MailService
import com.dpiataikin.notificationmailworker.core.usecase.UseCaseExecutor
import com.dpiataikin.notificationmailworker.core.usecase.notification.SendMailUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class MailServiceImpl(
    private val notificationViewMapper: NotificationViewMapper,
    private val useCaseExecutor: UseCaseExecutor,
    private val sendMailUseCase: SendMailUseCase
): MailService {
    override fun sendMail(createMailRequest: CreateMailRequest): Mono<VoidResponse> =
        useCaseExecutor.invoke(
            useCase = sendMailUseCase,
            requestDto = createMailRequest,
            requestConverter = { notificationViewMapper.toSendNotificationUseCaseRequest(it) },
            responseConverter = convertMonoToMono { notificationViewMapper.toVoidResponse() }
        )

    fun <T, R : Any> convertMonoToMono(mapperFunction: (T) -> R): (Mono<T>) -> Mono<R> = { mono ->
        mono.flatMap { item ->
            Mono.just(mapperFunction(item))
        }
    }
}
