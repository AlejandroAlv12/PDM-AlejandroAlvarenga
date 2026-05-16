package com.pdm0126.foodspot.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

interface CartRepository {
    val cartItems: StateFlow<List<CartItem>>
    fun addItem(dish: Dish, restaurantName: String)
    fun removeItem(dishId: Int)
    fun clearCart()
}

class MemoryCartRepository : CartRepository {
    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    override val cartItems = _cartItems.asStateFlow()

    override fun addItem(dish: Dish, restaurantName: String) {
        _cartItems.update { current ->
            val existing = current.find { it.dish.id == dish.id }
            if (existing != null) {
                current.map {
                    if (it.dish.id == dish.id) it.copy(quantity = it.quantity + 1) else it
                }
            } else {
                current + CartItem(dish, restaurantName)
            }
        }
    }

    override fun removeItem(dishId: Int) {
        _cartItems.update { current ->
            val existing = current.find { it.dish.id == dishId }
            if (existing != null && existing.quantity > 1) {
                current.map {
                    if (it.dish.id == dishId) it.copy(quantity = it.quantity - 1) else it
                }
            } else {
                current.filterNot { it.dish.id == dishId }
            }
        }
    }

    override fun clearCart() {
        _cartItems.value = emptyList()
    }
}
