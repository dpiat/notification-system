package com.dpiataikin.notificationservice.application.mapper

import com.dpiataikin.notificationservice.api.payload.ContactPayload
import com.dpiataikin.notificationservice.api.payload.request.CreateContactRequest
import com.dpiataikin.notificationservice.api.payload.response.ContactResponse
import com.dpiataikin.notificationservice.api.payload.response.ListContactResponse
import com.dpiataikin.notificationservice.core.domain.Contact
import com.dpiataikin.notificationservice.core.usecase.contact.CreateContactUseCase
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface ContactViewMapper {
    fun toContact(createContactRequest: CreateContactRequest): Contact

    fun toContactPayload(contact: Contact): ContactPayload
}

fun ContactViewMapper.toContactResponse(contact: Contact): ContactResponse =
    ContactResponse.create(toContactPayload(contact))

fun ContactViewMapper.toListContactPayload(contacts: List<Contact>): List<ContactPayload> =
    contacts.stream().map { toContactPayload(it) }.toList()

fun ContactViewMapper.toCreateContactUseCaseRequest(createContactRequest: CreateContactRequest): CreateContactUseCase.Request =
    CreateContactUseCase.Request(toContact(createContactRequest))

fun ContactViewMapper.toListContactResponse(contacts: List<Contact>): ListContactResponse =
    ListContactResponse.create(toListContactPayload(contacts))
