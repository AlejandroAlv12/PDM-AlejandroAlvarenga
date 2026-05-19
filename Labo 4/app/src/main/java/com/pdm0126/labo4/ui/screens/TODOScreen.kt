package com.pdm0126.labo4.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdm0126.labo4.model.Card
import com.pdm0126.labo4.viewmodel.GeneralViewModel

@Composable
fun TODOScreen(viewModel: GeneralViewModel) {
    val tasks = viewModel.tasks.collectAsState()
    val lista = mutableListOf<Card>()


}
