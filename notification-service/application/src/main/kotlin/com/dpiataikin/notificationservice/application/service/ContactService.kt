package com.dpiataikin.notificationservice.application.service

import com.dpiataikin.notificationservice.api.payload.request.CreateContactRequest
import com.dpiataikin.notificationservice.api.payload.response.ContactResponse
import com.dpiataikin.notificationservice.api.payload.response.ListContactResponse
import com.dpiataikin.notificationservice.core.domain.Contact
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
interface ContactService {
    fun createContact(createContactRequest: CreateContactRequest): Mono<ContactResponse>
    fun getContacts(userId: String): Mono<ListContactResponse>
}