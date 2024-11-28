package com.dpiataikin.notificationservice.core.domain

data class Contact(
    val id: String,
    val userId: String,
    val value: String,
    val type: ContactType
)

enum class ContactType {
    EMAIL
}