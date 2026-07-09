package com.pdm0126.labo6.data.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class VoteRequestDto(
    val votes: List<VoteItemDto>
)

@Serializable
data class VoteItemDto(
    val questionId: Int,
    val optionId: Int
)

@Serializable
data class VoteResponseDto(
    val updated: List<UpdatedOptionDto>
)

@Serializable
data class UpdatedOptionDto(
    val id: Int,
    val questionId: Int,
    val votes: Int
)
