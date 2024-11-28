package com.dpiataikin.notificationservice.application.rest

import com.dpiataikin.notificationservice.application.service.ContactService
import com.dpiataikin.notificationservice.core.domain.Contact
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping
class ContactController(private val contactService: ContactService) {
    @PostMapping("/contacts")
    fun createContact(@RequestBody contact: Contact): Mono<Contact> {
        return contactService.createContact(contact)
    }

    @GetMapping("/users/{userId}/contacts")
    fun getContacts(@PathVariable("userId") userId: String): Mono<List<Contact>> {
        return contactService.getContacts(userId)
    }
}