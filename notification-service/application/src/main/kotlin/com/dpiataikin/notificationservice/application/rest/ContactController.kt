package com.dpiataikin.notificationservice.application.rest

import com.dpiataikin.notificationservice.api.payload.request.CreateContactRequest
import com.dpiataikin.notificationservice.api.payload.response.ContactResponse
import com.dpiataikin.notificationservice.api.payload.response.ListContactResponse
import com.dpiataikin.notificationservice.application.service.ContactService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping
class ContactController(private val contactService: ContactService) {
    @PostMapping("/contacts")
    fun createContact(@RequestBody createContactRequest: CreateContactRequest): Mono<ContactResponse> {
        return contactService.createContact(createContactRequest)
    }

    @GetMapping("/users/{userId}/contacts")
    fun getContacts(@PathVariable("userId") userId: String): Mono<ListContactResponse> {
        return contactService.getContacts(userId)
    }
}