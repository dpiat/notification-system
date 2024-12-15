package com.dpiataikin.notificationmailworker.infrastructure.domain

import com.dpiataikin.notificationmailworker.core.domain.NotificationLogStatus
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Transient
import org.springframework.data.domain.Persistable
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Suppress("INAPPLICABLE_JVM_NAME")
@Table(name = "notification_logs")
data class NotificationLogEntity(
    @Id
    @Column(value = "id")
    var id: String,
    val notificationId: String,
    var status: NotificationLogStatus,
) : Persistable<String> {
    @Transient
    var isNeww: Boolean = false
    @JvmName("getEntityId")
    override fun getId(): String = id
    @JvmName("getIdffffffffffsNew")
    override fun isNew(): Boolean = isNeww
}
