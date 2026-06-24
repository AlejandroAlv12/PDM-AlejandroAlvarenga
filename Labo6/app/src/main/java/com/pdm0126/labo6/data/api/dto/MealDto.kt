package com.pdm0126.labo6.data.api.dto

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.pdm0126.labo6.data.model.Meal

@Serializable
data class MealDto(
    @SerialName("idMeal")
    @SerializedName("idMeal")
    val idMeal: String?,

    @SerialName("strMeal")
    @SerializedName("strMeal")
    val strMeal: String?,

    @SerialName("strCategory")
    @SerializedName("strCategory")
    val strCategory: String?,

    @SerialName("strArea")
    @SerializedName("strArea")
    val strArea: String?,

    @SerialName("strMealThumb")
    @SerializedName("strMealThumb")
    val strMealThumb: String?
)

fun MealDto.toModel(): Meal {
    return Meal(
        id = idMeal ?: "",
        name = strMeal ?: "Unknown",
        category = strCategory ?: "Unknown",
        area = strArea ?: "Unknown",
        thumbUrl = strMealThumb ?: ""
    )
}
