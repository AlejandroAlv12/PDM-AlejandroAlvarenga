package com.pdm0126.foodspot.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.pdm0126.foodspot.data.Restaurant
import com.pdm0126.foodspot.data.RestaurantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class HomeUiState(
    val categoriesWithRestaurants: Map<String, List<Restaurant>> = emptyMap()
)

class HomeViewModel(private val repository: RestaurantRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadRestaurants()
    }

    private fun loadRestaurants() {
        val allRestaurants = repository.getAllRestaurants()
        val categories = allRestaurants.flatMap { it.categories }.distinct()
        val grouped = categories.associateWith { category ->
            allRestaurants.filter { it.categories.contains(category) }
        }
        _uiState.value = HomeUiState(categoriesWithRestaurants = grouped)
    }
}
