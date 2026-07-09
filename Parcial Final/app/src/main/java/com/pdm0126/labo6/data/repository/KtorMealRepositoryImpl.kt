package com.pdm0126.labo6.data.repository

import com.pdm0126.labo6.data.api.KtorClient
import com.pdm0126.labo6.data.api.dto.MealResponseDto
import com.pdm0126.labo6.data.api.dto.toModel
import com.pdm0126.labo6.data.model.Meal
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorMealRepositoryImpl : MealRepository {
    override suspend fun getMeals(query: String): List<Meal> {
        return try {
            val response: MealResponseDto = KtorClient.client.get("https://www.themealdb.com/api/json/v1/1/search.php?s=$query").body()
            response.meals?.map { it.toModel() } ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
