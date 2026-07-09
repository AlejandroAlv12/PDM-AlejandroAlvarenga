package com.pdm0126.labo6.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdm0126.labo6.data.model.QuestionWithOptions
import com.pdm0126.labo6.data.repository.MassiveVoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class MassiveVoteUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val voteSuccess: Boolean = false,
    val submittedVotes: Map<Int, Int> = emptyMap() // questionId -> optionId
)

class MassiveVoteViewModel(
    private val repository: MassiveVoteRepository
) : ViewModel() {

    // Questions observed directly from Room
    val questions: StateFlow<List<QuestionWithOptions>> = repository.getQuestionsWithOptions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private val _uiState = MutableStateFlow(MassiveVoteUiState())
    val uiState: StateFlow<MassiveVoteUiState> = _uiState.asStateFlow()

    private val _selectedOptions = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val selectedOptions: StateFlow<Map<Int, Int>> = _selectedOptions.asStateFlow()

    fun selectOption(questionId: Int, optionId: Int) {
        _selectedOptions.value = _selectedOptions.value.toMutableMap().apply {
            put(questionId, optionId)
        }
    }

    fun refreshQuestions(apiKey: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                repository.refreshQuestions(apiKey)
                _uiState.value = _uiState.value.copy(isLoading = false)
            } catch (e: Exception) {
                // If it's 401, e.message might contain "401"
                val errorMsg = if (e.message?.contains("401") == true) {
                    "Error 401: API Key inválida o no autorizada"
                } else {
                    "Error de red: no se pudo sincronizar. Mostrando datos locales."
                }
                _uiState.value = _uiState.value.copy(isLoading = false, error = errorMsg)
            }
        }
    }

    fun submitVotes(apiKey: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val currentVotes = _selectedOptions.value
                repository.submitVotes(apiKey, currentVotes)
                // Write-through success
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    voteSuccess = true,
                    submittedVotes = currentVotes
                )
            } catch (e: Exception) {
                val errorMsg = if (e.message?.contains("401") == true) {
                    "Error 401: API Key inválida o no autorizada"
                } else if (e.message?.contains("400") == true) {
                    "Error 400: Datos inválidos. Verifica que seleccionaste una opción por pregunta."
                } else {
                    "Error de red al enviar el voto: ${e.message}"
                }
                _uiState.value = _uiState.value.copy(isLoading = false, error = errorMsg)
            }
        }
    }

    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    fun resetSuccess() {
        _uiState.value = _uiState.value.copy(voteSuccess = false)
        _selectedOptions.value = emptyMap()
    }
}
