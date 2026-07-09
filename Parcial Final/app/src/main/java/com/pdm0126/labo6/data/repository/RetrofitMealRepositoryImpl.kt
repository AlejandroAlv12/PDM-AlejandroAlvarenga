package com.pdm0126.labo6.data.repository

import com.pdm0126.labo6.data.api.RetrofitClient
import com.pdm0126.labo6.data.api.dto.toModel
import com.pdm0126.labo6.data.model.Meal

class RetrofitMealRepositoryImpl : MealRepository {
    override suspend fun getMeals(query: String): List<Meal> {
        return try {
            val response = RetrofitClient.api.searchMeals(query)
            response.meals?.map { it.toModel() } ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
