package com.dpiataikin.notificationservice.core.repository

import com.dpiataikin.notificationservice.core.usecase.notification.SendNotificationUseCase

interface NotificationRepository :
    SendNotificationUseCase.NotificationRepository
