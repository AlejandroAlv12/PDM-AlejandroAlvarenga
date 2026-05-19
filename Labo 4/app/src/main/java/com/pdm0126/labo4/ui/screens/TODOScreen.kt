package com.pdm0126.labo4.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdm0126.labo4.model.Card
import com.pdm0126.labo4.model.Task
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

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Agregar Nueva Tarea", modifier = Modifier.padding(bottom = 8.dp))

        OutlinedTextField(
            value = newCard.value.title,
            onValueChange = { newCard.value = newCard.value.copy(title = it) },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = newCard.value.description,
            onValueChange = { newCard.value = newCard.value.copy(description = it) },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val task = Task(
                    id = newCard.value.pos,
                    title = newCard.value.title,
                    description = newCard.value.description,
                    endDate = newCard.value.endDate,
                    isCompleted = newCard.value.checked
                )
                viewModel.addTask(task)

                newCard.value = Card(tasks.value.size + 2, "", "", Date(), false)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Tarea")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Lista de tareas", modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn {
            items(lista) { item ->
                Text(text = "${item.pos}: ${item.title} - ${if (item.checked) "Completada" else "Pendiente"}")
            }
        }
    }
}

