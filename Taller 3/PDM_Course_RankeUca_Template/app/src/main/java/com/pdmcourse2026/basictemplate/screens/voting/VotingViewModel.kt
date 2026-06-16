package com.pdmcourse2026.basictemplate.screens.voting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdmcourse2026.basictemplate.BasicTemplateApplication
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaRepository
import com.pdmcourse2026.basictemplate.data.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VotingViewModel(
    private val repository: RankeUcaRepository,
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(VotingUiState())
    val uiState: StateFlow<VotingUiState> = _uiState.asStateFlow()

    init {
        observeData()
        loadPlaces()
    }

    private fun observeData() {
        viewModelScope.launch {
            combine(
                repository.getPlacesFlow(),
                userPreferencesRepository.hasVoted,
                userPreferencesRepository.selectedPlaceId
            ) { places, hasVoted, selectedId ->
                Triple(places, hasVoted, selectedId)
            }.collect { (places, hasVoted, selectedId) ->
                _uiState.update { state ->
                    state.copy(
                        places = places.map { it.copy(isSelected = it.id == selectedId) },
                        isVoteSuccessful = hasVoted,
                        selectedPlaceId = selectedId
                    )
                }
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
        if (_uiState.value.isVoteSuccessful || _uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            repository.voteForPlace(placeId)
                .onSuccess {
                    // Persistimos el estado del voto localmente
                    userPreferencesRepository.saveSelectedPlaceId(placeId)
                    userPreferencesRepository.saveVotedState(true)
                    _uiState.update { it.copy(isLoading = false) }
                }
                .onFailure { error ->
                    _uiState.update { it.copy(isLoading = false, error = error.message) }
                }
        }
    }

    fun resetVote() {
        viewModelScope.launch {
            userPreferencesRepository.clearPreferences()
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BasicTemplateApplication)
                VotingViewModel(
                    repository = application.appProvider.rankeUcaRepository,
                    userPreferencesRepository = application.appProvider.userPreferencesRepository
                )
            }
        }
    }
}
