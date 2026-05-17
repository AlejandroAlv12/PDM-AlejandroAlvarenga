package com.pdm0126.foodspot.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.foodspot.data.CartRepository
import com.pdm0126.foodspot.data.Restaurant
import com.pdm0126.foodspot.data.RestaurantRepository
import kotlinx.coroutines.flow.*

data class HomeUiState(
    val categoriesWithRestaurants: Map<String, List<Restaurant>> = emptyMap()
)

class HomeViewModel(
    private val repository: RestaurantRepository,
    cartRepository: CartRepository
) : ViewModel() {
    var scrollIndex = 0
    var scrollOffset = 0

    val uiState: StateFlow<HomeUiState> = flow {
        val all = repository.getAllRestaurants()
        val categories = all.flatMap { it.categories }.distinct()
        val grouped = categories.associateWith { cat -> all.filter { it.categories.contains(cat) } }
        emit(HomeUiState(grouped))
    }.stateIn(viewModelScope, SharingStarted.Eagerly, HomeUiState())

    val cartItemCount = cartRepository.cartItems
        .map { it.sumOf { item -> item.quantity } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)
}
