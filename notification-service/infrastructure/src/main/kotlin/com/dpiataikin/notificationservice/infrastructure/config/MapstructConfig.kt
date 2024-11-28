package com.dpiataikin.notificationservice.infrastructure.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["com.dpiataikin.notificationservice.infrastructure.mapper"])
open class MapstructConfig