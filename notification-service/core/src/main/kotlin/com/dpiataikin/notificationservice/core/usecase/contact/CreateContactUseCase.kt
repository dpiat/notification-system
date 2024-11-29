package com.dpiataikin.notificationservice.core.usecase.contact

import com.dpiataikin.notificationservice.core.usecase.UseCase
import com.dpiataikin.notificationservice.core.domain.Contact
import reactor.core.publisher.Mono

class CreateContactUseCase(
    private val contactRepository: ContactRepository
) : UseCase<CreateContactUseCase.Request, Mono<CreateContactUseCase.Response>> {
    override fun execute(request: Request): Mono<Response> {
        return contactRepository.save(request.contact)
            .flatMap { contact -> Mono.just(Response(contact)) }
    }

    data class Request(
        val contact: Contact,
    )

    data class Response(
        val contact: Contact
    )

    interface ContactRepository {
        fun save(contact: Contact): Mono<Contact>
    }
}
