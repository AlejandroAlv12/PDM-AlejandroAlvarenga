package com.pdm0126.labo6.data.api

import com.pdm0126.labo6.data.api.dto.MealResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): MealResponseDto
}
