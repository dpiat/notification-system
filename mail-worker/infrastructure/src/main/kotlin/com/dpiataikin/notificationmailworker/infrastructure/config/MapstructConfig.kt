package com.dpiataikin.notificationmailworker.infrastructure.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.dpiataikin.notificationmailworker.infrastructure.mapper"])
open class MapstructConfig