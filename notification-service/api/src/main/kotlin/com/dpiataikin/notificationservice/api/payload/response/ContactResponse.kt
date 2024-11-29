package com.dpiataikin.notificationservice.api.payload.response

import com.dpiataikin.microservice.common.response.CommonResponse
import com.dpiataikin.notificationservice.api.payload.ContactPayload

class ContactResponse: CommonResponse<ContactPayload>() {
    companion object {
        fun create(payload: ContactPayload): ContactResponse {
            val response = ContactResponse()
            response.payload = payload
            return response
        }
    }
}