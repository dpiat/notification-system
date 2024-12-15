package com.dpiataikin.notificationmailworker.core.domain

data class NotificationLog(
    val id: String,
    val notificationId: String,
    var status: NotificationLogStatus
)

enum class NotificationLogStatus {
    HANDLED_BY_WORKER,
    SENT,
    SENT_ERROR
}
