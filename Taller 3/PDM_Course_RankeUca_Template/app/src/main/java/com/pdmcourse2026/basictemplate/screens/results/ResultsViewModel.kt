package com.pdmcourse2026.basictemplate.screens.results

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaRepository
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import kotlinx.coroutines.flow.collectLatest

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pdmcourse2026.basictemplate.BasicTemplateApplication

class ResultsViewModel(
    private val repository: RankeUcaRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(ResultsUiState())
    val uiState: StateFlow<ResultsUiState> = _uiState.asStateFlow()

    init {
        observeResults()
        loadResults()
    }

    private fun observeResults() {
        viewModelScope.launch {
            repository.getPlacesFlow().collectLatest { places ->
                val sortedPlaces = places.sortedByDescending { it.votes }
                _uiState.update { it.copy(places = sortedPlaces) }
            }
        }
    }

    fun loadResults() {
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

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BasicTemplateApplication)
                ResultsViewModel(repository = application.appProvider.rankeUcaRepository)
            }
        }
    }
}
