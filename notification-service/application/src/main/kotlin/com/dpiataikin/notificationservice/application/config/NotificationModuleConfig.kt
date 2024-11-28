package com.dpiataikin.notificationservice.application.config

import com.dpiataikin.notificationservice.core.repository.ContactRepository
import com.dpiataikin.notificationservice.core.usecase.UseCaseExecutorImp
import com.dpiataikin.notificationservice.core.usecase.contact.CreateContactUseCase
import com.dpiataikin.notificationservice.core.usecase.contact.GetContactsUseCase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class BotModuleConfig {
    @Bean
    open fun useCaseExecutor() = UseCaseExecutorImp()

    @Bean
    open fun createContactUseCase(
        contactRepository: ContactRepository
    ) = CreateContactUseCase(contactRepository)

    @Bean
    open fun getContactsUseCase(
        contactRepository: ContactRepository
    ) = GetContactsUseCase(contactRepository)
}
