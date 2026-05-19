package com.pdm0126.labo4.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdm0126.labo4.model.Card
import com.pdm0126.labo4.viewmodel.GeneralViewModel
import java.util.Date

@Composable
fun TODOScreen(viewModel: GeneralViewModel) {
    val tasks = viewModel.tasks.collectAsState()

    val newCard = remember {
        mutableStateOf(Card(tasks.value.size + 1, "", "", Date(), false))
    }

    val lista = mutableListOf<Card>()

    tasks.value.forEach { task ->
        Log.d("Task", task.toString())
        lista.add(
            Card(
                pos = task.id,
                title = task.title,
                description = task.description,
                endDate = task.endDate,
                checked = task.isCompleted
            )
        )
    }

        Text(text = "Lista de tareas", modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn {
            items(lista) { item ->
                Text(text = "${item.pos}: ${item.title} - ${if (item.checked) "Completada" else "Pendiente"}")
            }
        }
    }

