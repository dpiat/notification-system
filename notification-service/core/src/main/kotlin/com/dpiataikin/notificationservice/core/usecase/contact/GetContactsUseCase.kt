package com.dpiataikin.notificationservice.core.usecase.contact

import com.dpiataikin.notificationservice.core.domain.Contact
import com.dpiataikin.notificationservice.core.usecase.UseCase
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

class GetContactsUseCase(
    private val contactRepository: ContactRepository
) : UseCase<GetContactsUseCase.Request, Mono<GetContactsUseCase.Response>> {
    override fun execute(request: Request): Mono<Response> {
        return contactRepository.findAllContactsByUserId(request.userId)
            .collectList()
            .flatMap { contacts -> Mono.just(Response(contacts)) }
    }

    data class Request(
        val userId: String,
    )

    data class Response(
        val contacts: List<Contact>
    )

    interface ContactRepository {
        fun findAllContactsByUserId(userId: String): Flux<Contact>
    }
}
