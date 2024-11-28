package com.dpiataikin.notificationservice.application.service.impl

import com.dpiataikin.notificationservice.application.service.ContactService
import com.dpiataikin.notificationservice.core.usecase.UseCaseExecutor
import com.dpiataikin.notificationservice.core.domain.Contact
import com.dpiataikin.notificationservice.core.usecase.contact.CreateContactUseCase
import com.dpiataikin.notificationservice.core.usecase.contact.GetContactsUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ContactServiceImpl(
    private val useCaseExecutor: UseCaseExecutor,
    private val createContactUseCase: CreateContactUseCase,
    private val getContactsUseCase: GetContactsUseCase
) : ContactService {
    override fun createContact(contact: Contact): Mono<Contact> =
        useCaseExecutor.invoke(
            useCase = createContactUseCase,
            requestDto = contact,
            requestConverter = { CreateContactUseCase.Request(it) },
            responseConverter = { mono: Mono<CreateContactUseCase.Response> ->
                mono.flatMap { contact ->
                    Mono.just(contact.contact)
                }
            }
        )

    override fun getContacts(userId: String): Mono<List<Contact>> =
        useCaseExecutor.invoke(
            useCase = getContactsUseCase,
            requestDto = userId,
            requestConverter = { GetContactsUseCase.Request(it) },
            responseConverter = { mono: Mono<GetContactsUseCase.Response> ->
                mono.flatMap { contacts ->
                    Mono.just(contacts.contacts)
                }
            }
        )
}