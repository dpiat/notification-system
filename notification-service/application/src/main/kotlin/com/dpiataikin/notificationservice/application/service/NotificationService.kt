package com.dpiataikin.notificationservice.application.service

import com.dpiataikin.microservice.common.response.VoidResponse
import com.dpiataikin.notificationservice.api.payload.request.CreateNotificationRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
interface NotificationService {
    fun createNotification(createNotificationRequest: CreateNotificationRequest): Mono<VoidResponse>
}