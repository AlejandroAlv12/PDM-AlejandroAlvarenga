package com.pdm0126.labo6.data.repository

import com.pdm0126.labo6.data.model.Meal

interface MealRepository {
    suspend fun getMeals(query: String): List<Meal>
}
