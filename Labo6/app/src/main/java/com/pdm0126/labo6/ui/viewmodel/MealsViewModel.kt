package com.pdm0126.labo6.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.labo6.data.model.Meal
import com.pdm0126.labo6.data.repository.KtorMealRepositoryImpl
import com.pdm0126.labo6.data.repository.MealRepository
import com.pdm0126.labo6.data.repository.RetrofitMealRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MealsViewModel : ViewModel() {
    private val ktorRepository: MealRepository = KtorMealRepositoryImpl()
    private val retrofitRepository: MealRepository = RetrofitMealRepositoryImpl()

    private val _meals = MutableStateFlow<List<Meal>>(emptyList())
    val meals: StateFlow<List<Meal>> = _meals.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _currentClient = MutableStateFlow("Ninguno")
    val currentClient: StateFlow<String> = _currentClient.asStateFlow()

    fun fetchMealsWithKtor(query: String = "") {
        _currentClient.value = "Ktor"
        fetchMeals(ktorRepository, query)
    }

    fun fetchMealsWithRetrofit(query: String = "") {
        _currentClient.value = "Retrofit"
        fetchMeals(retrofitRepository, query)
    }

    private fun fetchMeals(repository: MealRepository, query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getMeals(query)
                _meals.value = result
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                _isLoading.value = false
            }
        }
    }
}
