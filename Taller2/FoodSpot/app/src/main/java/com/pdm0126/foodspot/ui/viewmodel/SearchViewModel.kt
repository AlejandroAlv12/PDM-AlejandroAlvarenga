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

data class SearchUiState(
    val query: String = "",
    val searchResults: List<Restaurant> = emptyList(),
    val hasSearched: Boolean = false
)

class SearchViewModel(
    private val repository: RestaurantRepository,
    private val cartRepository: CartRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    val cartItemCount: StateFlow<Int> = cartRepository.cartItems
        .map { items -> items.sumOf { it.quantity } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), 0)

    fun onQueryChange(newQuery: String) {
        val results = if (newQuery.isBlank()) {
            emptyList()
        } else {
            repository.searchRestaurants(newQuery)
        }
        _uiState.value = _uiState.value.copy(
            query = newQuery,
            searchResults = results,
            hasSearched = true
        )
    }
}
