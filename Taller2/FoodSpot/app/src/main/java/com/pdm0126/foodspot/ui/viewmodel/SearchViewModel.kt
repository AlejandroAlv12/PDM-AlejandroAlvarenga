package com.pdm0126.foodspot.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.foodspot.data.CartRepository
import com.pdm0126.foodspot.data.Restaurant
import com.pdm0126.foodspot.data.RestaurantRepository
import kotlinx.coroutines.flow.*

data class SearchUiState(
    val query: String = "",
    val searchResults: List<Restaurant> = emptyList(),
    val hasSearched: Boolean = false
)

class SearchViewModel(
    private val repository: RestaurantRepository,
    cartRepository: CartRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    val cartItemCount = cartRepository.cartItems
        .map { it.sumOf { item -> item.quantity } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun onQueryChange(newQuery: String) {
        val results = if (newQuery.isBlank()) emptyList() else repository.searchRestaurants(newQuery)
        _uiState.update { it.copy(query = newQuery, searchResults = results, hasSearched = true) }
    }
}
