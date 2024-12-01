package com.dpiataikin.notificationmailworker.infrastructure.domain

import org.springframework.data.relational.core.mapping.Table

@Table(name = "notification_logs")
data class NotificationLogEntity(
    val notificationId: String
)
