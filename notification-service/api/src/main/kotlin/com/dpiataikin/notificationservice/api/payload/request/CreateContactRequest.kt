package com.dpiataikin.notificationservice.api.payload.request

data class CreateContactRequest (
    val id: String,
    val userId: String,
    val value: String,
    val type: String
)
