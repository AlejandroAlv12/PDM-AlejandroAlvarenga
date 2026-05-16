package com.pdm0126.foodspot.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.foodspot.data.CartRepository
import com.pdm0126.foodspot.data.Restaurant
import com.pdm0126.foodspot.data.RestaurantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class HomeUiState(
    val categoriesWithRestaurants: Map<String, List<Restaurant>> = emptyMap()
)

class HomeViewModel(
    private val repository: RestaurantRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    val cartItemCount: StateFlow<Int> = cartRepository.cartItems
        .map { items -> items.sumOf { it.quantity } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

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
