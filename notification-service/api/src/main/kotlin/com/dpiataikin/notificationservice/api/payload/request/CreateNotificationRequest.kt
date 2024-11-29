package com.dpiataikin.notificationservice.api.payload.request

data class CreateNotificationRequest(
    val userFrom: String,
    val userTo: List<String>,
    val subject: String,
    val body: String
)