package com.dpiataikin.notificationservice.infrastructure.domain

import com.dpiataikin.notificationservice.core.domain.ContactType
import org.springframework.data.mongodb.core.mapping.Document

@Document("contacts")
class ContactEntity(
    val id: String,
    val userId: String,
    val value: String,
    val type: ContactType
)