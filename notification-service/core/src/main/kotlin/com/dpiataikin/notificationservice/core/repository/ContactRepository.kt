package com.dpiataikin.notificationservice.core.repository

import com.dpiataikin.notificationservice.core.usecase.contact.CreateContactUseCase
import com.dpiataikin.notificationservice.core.usecase.contact.GetContactsUseCase

interface ContactRepository:
    CreateContactUseCase.ContactRepository,
    GetContactsUseCase.ContactRepository
