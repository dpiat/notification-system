package com.dpiataikin.notificationmailworker.core.repository

import com.dpiataikin.notificationmailworker.core.usecase.notification.SendMailUseCase

interface NotificationLogRepository :
    SendMailUseCase.NotificationLogRepository
