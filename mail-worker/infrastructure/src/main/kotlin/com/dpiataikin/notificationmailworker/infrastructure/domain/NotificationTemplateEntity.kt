package com.dpiataikin.notificationmailworker.infrastructure.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document("mail_templates")
class NotificationTemplateEntity(
    val id: String,
    val value: String
)