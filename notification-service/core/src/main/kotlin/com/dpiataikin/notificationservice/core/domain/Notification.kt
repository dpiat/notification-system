package com.dpiataikin.notificationservice.core.domain

class Notification(
    val userFrom: String,
    val userTo: List<String>,
    val subject: String,
    val body: String
)
