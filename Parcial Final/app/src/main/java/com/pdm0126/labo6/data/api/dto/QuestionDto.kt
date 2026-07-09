package com.pdm0126.labo6.data.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuestionDto(
    val id: Int,
    val text: String,
    val options: List<OptionDto>
)

@Serializable
data class OptionDto(
    val id: Int,
    val value: String,
    val votes: Int
)
