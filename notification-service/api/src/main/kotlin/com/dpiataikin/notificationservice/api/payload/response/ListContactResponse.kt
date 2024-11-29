package com.dpiataikin.notificationservice.api.payload.response

import com.dpiataikin.microservice.common.response.CommonResponse
import com.dpiataikin.notificationservice.api.payload.ContactPayload

class ListContactResponse: CommonResponse<List<ContactPayload>>() {
    companion object {
        fun create(payload: List<ContactPayload>): ListContactResponse {
            val response = ListContactResponse()
            response.payload = payload
            return response
        }
    }
}