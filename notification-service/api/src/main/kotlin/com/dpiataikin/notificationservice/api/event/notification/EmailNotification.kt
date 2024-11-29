package com.dpiataikin.notificationservice.api.event.notification

import com.dpiataikin.microservice.common.event.starter.model.DomainEvent

data class EmailNotification(
    val id: String,
    val userFrom: String,
    val userTo: String,
    val subject: String,
    val body: String
): DomainEvent