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

class CartViewModel(private val cartRepo: CartRepository) : ViewModel() {
    val uiState = cartRepo.cartItems
        .map { items -> CartUiState(items, items.sumOf { it.quantity * it.dish.price }) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CartUiState())

    fun removeItem(dishId: Int) = cartRepo.removeItem(dishId)
    fun clearCart() = cartRepo.clearCart()
}
