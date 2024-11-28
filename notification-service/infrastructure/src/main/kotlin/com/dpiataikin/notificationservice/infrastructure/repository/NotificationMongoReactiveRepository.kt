package com.dpiataikin.notificationservice.infrastructure.repository

import com.dpiataikin.notificationservice.core.domain.Notification
import org.springframework.data.mongodb.repository.ReactiveMongoRepository

interface NotificationMongoReactiveRepository : ReactiveMongoRepository<Notification, String>
