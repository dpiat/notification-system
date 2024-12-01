package com.dpiataikin.notificationmailworker.application.api.rest

import com.dpiataikin.microservice.common.response.VoidResponse
import com.dpiataikin.notificationservice.api.payload.request.CreateMailRequest
import com.dpiataikin.notificationmailworker.application.service.MailService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping
class NotificationController(
    private val mailService: MailService
) {
    @PostMapping("/notifications/mail")
    fun createContact(@RequestBody createMailRequest: CreateMailRequest): Mono<VoidResponse> {
        return mailService.sendMail(createMailRequest)
    }
}