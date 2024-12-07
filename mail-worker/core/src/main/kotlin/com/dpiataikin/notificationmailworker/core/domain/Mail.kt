package com.dpiataikin.notificationmailworker.core.domain

data class Mail(
    val id: String,
    val userFrom: String,
    val userTo: String,
    val subject: String,
    val body: String
)
