package com.dpiataikin.notificationservice.infrastructure.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EntityScan(basePackages = ["com.dpiataikin.notificationservice.infrastructure.domain"])
@EnableReactiveMongoRepositories(basePackages = ["com.dpiataikin.notificationservice.infrastructure.repository"])
open class DatabaseConfig