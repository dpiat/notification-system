package com.dpiataikin.notificationservice.api.event

import com.dpiataikin.microservice.common.event.starter.model.DomainEventType
import com.dpiataikin.notificationservice.api.event.notification.EmailNotification

enum class NotificationEventType(
    private val eventName: String,
    private val eventType: Class<*>
) : DomainEventType {
    EMAIL_NOTIFICATION("email_notification", EmailNotification::class.java);

    override fun getName(): String = eventName
    override fun getType(): Class<*> = eventType
}