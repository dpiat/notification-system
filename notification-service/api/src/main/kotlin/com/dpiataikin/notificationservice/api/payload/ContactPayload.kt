package com.dpiataikin.notificationservice.api.payload

data class ContactPayload(
    val id: String,
    val userId: String,
    val value: String,
    val type: String
)