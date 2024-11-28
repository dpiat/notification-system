package com.dpiataikin.notificationservice.infrastructure.domain

class NotificationEntity(
    val id: String,
    val userFrom: String,
    val userTo: List<String>,
    val subject: String,
    val body: String
)
