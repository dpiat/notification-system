package com.dpiataikin.notificationservice.infrastructure.repository.impl

import com.dpiataikin.notificationservice.core.domain.Contact
import com.dpiataikin.notificationservice.core.repository.ContactRepository
import com.dpiataikin.notificationservice.infrastructure.mapper.ContactDatabaseMapper
import com.dpiataikin.notificationservice.infrastructure.repository.ContactMongoReactiveRepository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

open class ContactRepositoryImpl(
    private val contactMongoReactiveRepository: ContactMongoReactiveRepository,
    private val contactDatabaseMapper: ContactDatabaseMapper
): ContactRepository {
    override fun save(contact: Contact): Mono<Contact> {
        return Mono.just(contact)
            .flatMap { c -> Mono.just(contactDatabaseMapper.toContactEntity(c)) }
            .flatMap { contactEntity -> contactMongoReactiveRepository.save(contactEntity) }
            .flatMap { contactEntity -> Mono.just(contactDatabaseMapper.toContact(contactEntity)) }
    }

    override fun findAllContactsByUserId(userId: String): Flux<Contact> {
        return contactMongoReactiveRepository.findAllByUserId(userId)
            .flatMap { contactEntity -> Mono.just(contactDatabaseMapper.toContact(contactEntity)) }
    }

    override fun findAllContactsByUserIdIn(userIds: List<String>): Flux<Contact> {
        return contactMongoReactiveRepository.findAllByUserIdIn(userIds)
            .flatMap { contactEntity -> Mono.just(contactDatabaseMapper.toContact(contactEntity)) }
    }
}
