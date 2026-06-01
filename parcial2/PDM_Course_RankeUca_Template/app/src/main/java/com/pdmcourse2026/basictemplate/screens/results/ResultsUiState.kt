package com.pdmcourse2026.basictemplate.screens.results

import com.pdmcourse2026.basictemplate.ui.models.PlaceModel

data class ResultsUiState(
    val isLoading: Boolean = false,
    val places: List<PlaceModel> = emptyList(),
    val error: String? = null
)
