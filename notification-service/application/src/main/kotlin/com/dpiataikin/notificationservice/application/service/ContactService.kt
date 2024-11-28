package com.dpiataikin.notificationservice.application.service

import com.dpiataikin.notificationservice.core.domain.Contact
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
interface ContactService {
    fun createContact(contact: Contact): Mono<Contact>
    fun getContacts(userId: String): Mono<List<Contact>>
}