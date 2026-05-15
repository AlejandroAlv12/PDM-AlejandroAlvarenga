package com.pdm0126.foodspot.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.pdm0126.foodspot.data.Restaurant
import com.pdm0126.foodspot.data.RestaurantRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class SearchUiState(
    val query: String = "",
    val searchResults: List<Restaurant> = emptyList(),
    val hasSearched: Boolean = false
)

class SearchViewModel(private val repository: RestaurantRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

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
