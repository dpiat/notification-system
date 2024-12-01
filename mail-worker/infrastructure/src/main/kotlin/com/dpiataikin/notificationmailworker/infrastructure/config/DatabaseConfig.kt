package com.dpiataikin.notificationmailworker.infrastructure.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EntityScan(basePackages = ["com.dpiataikin.notificationservice.infrastructure.domain"])
@EnableR2dbcRepositories(basePackages = ["com.dpiataikin.notificationmailworker.infrastructure.repository"])
open class DatabaseConfig