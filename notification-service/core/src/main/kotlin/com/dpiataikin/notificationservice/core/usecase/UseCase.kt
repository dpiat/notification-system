package com.dpiataikin.notificationservice.core.usecase

import reactor.core.publisher.Mono
import java.util.function.Function

interface UseCase<in Request, out Response> {
    fun execute(request: Request): Response
}

interface UseCaseExecutor {
    operator fun <RequestDto, ResponseDto, Request, Response> invoke(
        useCase: UseCase<Request, Response>,
        requestDto: RequestDto,
        requestConverter: (RequestDto) -> Request,
        responseConverter: (Response) -> ResponseDto
    ): ResponseDto

    operator fun <RequestDto, Request> invoke(
        useCase: UseCase<Request, Unit>,
        requestDto: RequestDto,
        requestConverter: (RequestDto) -> Request
    ) =
        invoke(useCase, requestDto, requestConverter, {})

    operator fun <ResponseDto, Response> invoke(
        useCase: UseCase<Unit, Response>,
        responseConverter: (Response) -> ResponseDto
    ) =
        invoke(useCase, Unit, { }, responseConverter)
}


class UseCaseExecutorImp : UseCaseExecutor {
    override fun <RequestDto, ResponseDto, Request, Response> invoke(
        useCase: UseCase<Request, Response>,
        requestDto: RequestDto,
        requestConverter: (RequestDto) -> Request,
        responseConverter: (Response) -> ResponseDto
    ): ResponseDto {
        val request = requestConverter(requestDto)
        val response = useCase.execute(request)
        return responseConverter(response)
    }
}