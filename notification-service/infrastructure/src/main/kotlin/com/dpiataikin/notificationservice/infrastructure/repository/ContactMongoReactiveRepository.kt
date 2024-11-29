package com.dpiataikin.notificationservice.infrastructure.repository

import com.dpiataikin.notificationservice.infrastructure.domain.ContactEntity
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import reactor.core.publisher.Flux

interface ContactMongoReactiveRepository : ReactiveMongoRepository<ContactEntity, String> {
    fun findAllByUserId(userId: String): Flux<ContactEntity>
    fun findAllByUserIdIn(userIds: List<String>): Flux<ContactEntity>
}