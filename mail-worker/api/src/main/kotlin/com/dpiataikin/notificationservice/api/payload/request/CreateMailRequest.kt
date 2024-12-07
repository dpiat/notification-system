package com.dpiataikin.notificationservice.api.payload.request

data class CreateMailRequest(
    val id: String,
    val userFrom: String,
    val userTo: String,
    val subject: String,
    val body: String
)