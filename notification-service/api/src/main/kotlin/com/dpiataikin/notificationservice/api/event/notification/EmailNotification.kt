package com.dpiataikin.notificationservice.api.event.notification

import com.dpiataikin.microservice.common.event.starter.model.DomainEvent
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty

data class EmailNotification @JsonCreator constructor(
    @JsonProperty("id") val id: String,
    @JsonProperty("userFrom") val userFrom: String,
    @JsonProperty("userTo") val userTo: String,
    @JsonProperty("subject") val subject: String,
    @JsonProperty("body") val body: String
): DomainEvent