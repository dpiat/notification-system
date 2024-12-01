package com.dpiataikin.notificationmailworker.application.service

import com.dpiataikin.microservice.common.response.VoidResponse
import com.dpiataikin.notificationservice.api.payload.request.CreateMailRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
interface MailService {
    fun sendMail(createMailRequest: CreateMailRequest): Mono<VoidResponse>
}