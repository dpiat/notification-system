package com.dpiataikin.notificationservice.api.payload

data class NotificationPayload(
    val userFrom: String,
    val userTo: List<String>,
    val subject: String,
    val body: String
)