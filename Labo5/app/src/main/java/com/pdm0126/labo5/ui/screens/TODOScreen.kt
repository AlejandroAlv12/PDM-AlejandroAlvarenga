package com.pdm0126.labo5.ui.screens

import android.util.Log
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.material3.Card as M3Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pdm0126.labo5.model.Card
import com.pdm0126.labo5.model.Task
import com.pdm0126.labo5.viewmodel.GeneralViewModel
import java.util.Date

@Composable
fun TODOScreen(viewModel: GeneralViewModel) {
    val tasks = viewModel.tasks.collectAsState()

    val newCard = remember {
        mutableStateOf(Card(0, "", "", Date(), false))
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
                val nextId = tasks.value.size + 1
                val task = Task(
                    id = nextId,
                    title = newCard.value.title,
                    description = newCard.value.description,
                    endDate = newCard.value.endDate,
                    isCompleted = false
                )
                viewModel.addTask(task)

                newCard.value = Card(0, "", "", Date(), false)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Agregar Tarea")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(text = "Lista de tareas", modifier = Modifier.padding(bottom = 8.dp))
        LazyColumn {
            items(lista) { item ->
                M3Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (item.checked) {
                            if (isSystemInDarkTheme()) Color(0xFF1B5E20) else Color(0xFFC8E6C9)
                        } else MaterialTheme.colorScheme.surfaceVariant
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = item.checked,
                            onCheckedChange = { 
                                tasks.value.find { it.id == item.pos }?.let { task ->
                                    viewModel.toggleTask(task)
                                }
                            }
                        )
                        Column(modifier = Modifier.padding(start = 8.dp)) {
                            Text(
                                text = "${item.pos}. ${item.title}",
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = item.description,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }
        }
    }
}
