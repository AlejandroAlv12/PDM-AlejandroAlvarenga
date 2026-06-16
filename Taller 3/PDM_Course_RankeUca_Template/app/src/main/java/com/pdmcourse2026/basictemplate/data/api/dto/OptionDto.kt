package com.pdmcourse2026.basictemplate.data.api.dto

import kotlinx.serialization.Serializable

@Serializable
data class OptionDto(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val votes: Int
)
