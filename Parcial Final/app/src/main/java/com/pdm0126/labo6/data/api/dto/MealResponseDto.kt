package com.pdm0126.labo6.data.api.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealResponseDto(
    @SerialName("meals")
    @SerializedName("meals")
    val meals: List<MealDto>?
)
