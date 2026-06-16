package com.pdmcourse2026.basictemplate.data.api

import com.pdmcourse2026.basictemplate.data.api.dto.GenericResponseDto
import com.pdmcourse2026.basictemplate.data.api.dto.OptionDto
import com.pdmcourse2026.basictemplate.data.api.dto.VoteRequestDto
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RankeUcaService {
    private val client = KtorClient.client

    suspend fun getOptions(): List<OptionDto> {
        return client.get("options").body()
    }

    suspend fun vote(optionId: Int): GenericResponseDto {
        return client.post("vote") {
            contentType(ContentType.Application.Json)
            setBody(VoteRequestDto(optionId))
        }.body()
    }
}
