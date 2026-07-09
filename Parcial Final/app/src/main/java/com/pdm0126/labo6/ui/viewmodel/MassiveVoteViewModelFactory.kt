package com.pdm0126.labo6.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pdm0126.labo6.data.repository.MassiveVoteRepository

class MassiveVoteViewModelFactory(
    private val repository: MassiveVoteRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MassiveVoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MassiveVoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
