package com.dpiataikin.notificationmailworker.core.exception

class SendingHandledException: RuntimeException("Notification is already handled by other worker")