package com.dpiataikin.notificationservice.infrastructure.mapper

import com.dpiataikin.notificationservice.core.domain.Contact
import com.dpiataikin.notificationservice.infrastructure.domain.ContactEntity
import org.mapstruct.InjectionStrategy
import org.mapstruct.Mapper
import org.mapstruct.NullValuePropertyMappingStrategy


@Mapper(
    componentModel = "spring",
    injectionStrategy = InjectionStrategy.CONSTRUCTOR,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
interface ContactDatabaseMapper {
    fun toContactEntity(contact: Contact): ContactEntity
    fun toContact(contactEntity: ContactEntity): Contact
}
