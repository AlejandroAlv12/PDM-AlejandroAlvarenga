package com.pdm0126.foodspot.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.pdm0126.foodspot.data.Restaurant
import com.pdm0126.foodspot.data.RestaurantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class DetailUiState(
    val restaurant: Restaurant? = null,
    val isLoading: Boolean = true
)

class DetailViewModel(private val repository: RestaurantRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState: StateFlow<DetailUiState> = _uiState.asStateFlow()

    fun loadRestaurant(id: Int) {
        val restaurant = repository.getRestaurantById(id)
        _uiState.value = DetailUiState(restaurant = restaurant, isLoading = false)
    }
}
