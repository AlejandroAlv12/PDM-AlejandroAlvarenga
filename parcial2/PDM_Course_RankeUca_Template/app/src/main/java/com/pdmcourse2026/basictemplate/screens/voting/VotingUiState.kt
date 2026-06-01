package com.pdmcourse2026.basictemplate.screens.voting

import com.pdmcourse2026.basictemplate.ui.models.PlaceModel

data class VotingUiState(
    val isLoading: Boolean = false,
    val places: List<PlaceModel> = emptyList(),
    val error: String? = null,
    val selectedPlaceId: Int? = null,
    val isVoteSuccessful: Boolean = false
)
