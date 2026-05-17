package com.pdm0126.foodspot.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.pdm0126.foodspot.data.CartRepository
import com.pdm0126.foodspot.data.Dish
import com.pdm0126.foodspot.data.Restaurant
import com.pdm0126.foodspot.data.RestaurantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import androidx.lifecycle.viewModelScope

data class DetailUiState(
    val restaurant: Restaurant? = null,
    val isLoading: Boolean = true
)

class DetailViewModel(
    private val repository: RestaurantRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(DetailUiState())
    val uiState = _uiState.asStateFlow()

    val cartItemCount = cartRepository.cartItems
        .map { it.sumOf { item -> item.quantity } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun loadRestaurant(id: Int) {
        _uiState.value = DetailUiState(repository.getRestaurantById(id), false)
    }

    fun addToCart(dish: Dish) {
        uiState.value.restaurant?.let { cartRepository.addItem(dish, it.name) }
    }
}
