package com.dpiataikin.notificationservice.application.rest

import com.dpiataikin.microservice.common.response.VoidResponse
import com.dpiataikin.notificationservice.api.payload.request.CreateContactRequest
import com.dpiataikin.notificationservice.api.payload.request.CreateNotificationRequest
import com.dpiataikin.notificationservice.api.payload.response.ContactResponse
import com.dpiataikin.notificationservice.api.payload.response.ListContactResponse
import com.dpiataikin.notificationservice.application.service.ContactService
import com.dpiataikin.notificationservice.application.service.NotificationService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping
class NotificationController(
    private val notificationService: NotificationService
) {
    @PostMapping("/notifications")
    fun createContact(@RequestBody createNotificationRequest: CreateNotificationRequest): Mono<VoidResponse> {
        return notificationService.createNotification(createNotificationRequest)
    }
}