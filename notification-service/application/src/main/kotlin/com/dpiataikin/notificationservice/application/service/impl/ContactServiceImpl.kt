package com.dpiataikin.notificationservice.application.service.impl

import com.dpiataikin.notificationservice.api.payload.request.CreateContactRequest
import com.dpiataikin.notificationservice.api.payload.response.ContactResponse
import com.dpiataikin.notificationservice.api.payload.response.ListContactResponse
import com.dpiataikin.notificationservice.application.mapper.ContactViewMapper
import com.dpiataikin.notificationservice.application.mapper.toContactResponse
import com.dpiataikin.notificationservice.application.mapper.toCreateContactUseCaseRequest
import com.dpiataikin.notificationservice.application.mapper.toListContactResponse
import com.dpiataikin.notificationservice.application.service.ContactService
import com.dpiataikin.notificationservice.core.usecase.UseCaseExecutor
import com.dpiataikin.notificationservice.core.usecase.contact.CreateContactUseCase
import com.dpiataikin.notificationservice.core.usecase.contact.GetContactsUseCase
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ContactServiceImpl(
    private val contactViewMapper: ContactViewMapper,
    private val useCaseExecutor: UseCaseExecutor,
    private val createContactUseCase: CreateContactUseCase,
    private val getContactsUseCase: GetContactsUseCase
) : ContactService {
    override fun createContact(createContactRequest: CreateContactRequest): Mono<ContactResponse> =
        useCaseExecutor.invoke(
            useCase = createContactUseCase,
            requestDto = createContactRequest,
            requestConverter = { contactViewMapper.toCreateContactUseCaseRequest(it) },
            responseConverter = convertMonoToMono { contactViewMapper.toContactResponse(it.contact) }
        )

    override fun getContacts(userId: String): Mono<ListContactResponse> =
        useCaseExecutor.invoke(
            useCase = getContactsUseCase,
            requestDto = userId,
            requestConverter = { GetContactsUseCase.Request(it) },
            responseConverter = convertMonoToMono { contactViewMapper.toListContactResponse(it.contacts) }
        )

    fun <T, R : Any> convertMonoToMono(mapperFunction: (T) -> R): (Mono<T>) -> Mono<R> = { mono ->
        mono.flatMap { item ->
            Mono.just(mapperFunction(item))
        }
    }
}