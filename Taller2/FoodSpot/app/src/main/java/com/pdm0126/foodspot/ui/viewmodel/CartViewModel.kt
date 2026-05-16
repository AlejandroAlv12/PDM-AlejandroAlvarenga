package com.pdm0126.foodspot.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.foodspot.data.CartItem
import com.pdm0126.foodspot.data.CartRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

data class CartUiState(
    val items: List<CartItem> = emptyList(),
    val total: Double = 0.0
)

class CartViewModel(private val cartRepository: CartRepository) : ViewModel() {
    val uiState: StateFlow<CartUiState> = cartRepository.cartItems
        .map { items ->
            CartUiState(
                items = items,
                total = items.sumOf { it.quantity * it.dish.price }
            )
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CartUiState())

    fun removeItem(dishId: Int) {
        cartRepository.removeItem(dishId)
    }

    fun clearCart() {
        cartRepository.clearCart()
    }
}
