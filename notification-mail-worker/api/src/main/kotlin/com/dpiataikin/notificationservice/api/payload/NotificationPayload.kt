package com.dpiataikin.notificationservice.api.payload

data class MailPayload(
    val userFrom: String,
    val userTo: List<String>,
    val subject: String,
    val body: String
)