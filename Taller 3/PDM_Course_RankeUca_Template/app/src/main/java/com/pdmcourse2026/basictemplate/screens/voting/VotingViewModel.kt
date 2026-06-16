package com.pdmcourse2026.basictemplate.screens.voting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaRepository
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VotingViewModel(
    private val repository: RankeUcaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(VotingUiState())
    val uiState: StateFlow<VotingUiState> = _uiState.asStateFlow()

    init {
        observePlaces()
        loadPlaces()
    }

    private fun observePlaces() {
        viewModelScope.launch {
            repository.getPlacesFlow().collect { places ->
                _uiState.update { it.copy(places = places) }
            }
        }
    }

    fun loadPlaces() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            repository.fetchPlaces()
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false) }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }

    fun vote(placeId: Int) {
        if (_uiState.value.isVoteSuccessful) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            repository.voteForPlace(placeId)
                .onSuccess {
                    _uiState.update { state ->
                        state.copy(
                            isLoading = false,
                            selectedPlaceId = placeId,
                            isVoteSuccessful = true,
                            places = state.places.map { 
                                val isSelected = it.id == placeId
                                it.copy(isSelected = isSelected, votes = if (isSelected) it.votes + 1 else it.votes)
                            }
                        )
                    }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }

    fun resetVote() {
        _uiState.update { state ->
            state.copy(
                isVoteSuccessful = false,
                selectedPlaceId = null,
                places = state.places.map { it.copy(isSelected = false) }
            )
        }
    }
}
