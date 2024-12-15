package com.dpiataikin.notificationservice.core.repository

import com.dpiataikin.notificationservice.core.usecase.contact.CreateContactUseCase
import com.dpiataikin.notificationservice.core.usecase.contact.GetContactsUseCase
import com.dpiataikin.notificationservice.core.usecase.notification.SendNotificationUseCase

interface ContactRepository :
    CreateContactUseCase.ContactRepository,
    GetContactsUseCase.ContactRepository,
    SendNotificationUseCase.ContactRepository
