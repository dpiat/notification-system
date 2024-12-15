package com.dpiataikin.notificationmailworker.core.exception

class NotificationHandledException: RuntimeException("Notification is already handled by other worker")