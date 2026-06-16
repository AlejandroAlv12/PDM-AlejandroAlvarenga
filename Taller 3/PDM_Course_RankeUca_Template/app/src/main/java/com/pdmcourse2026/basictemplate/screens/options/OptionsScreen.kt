package com.pdmcourse2026.basictemplate.screens.options

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pdmcourse2026.basictemplate.data.model.Option

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsScreen(
    viewModel: OptionsViewModel,
    onBack: () -> Unit
) {
    val options by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Gestión de Opciones") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { 
                viewModel.addOption("Nueva Opción ${options.size + 1}", "https://via.placeholder.com/150") 
            }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(options) { option ->
                OptionItem(
                    option = option,
                    onDelete = { viewModel.deleteOption(option) }
                )
            }
        }
    }
}

@Composable
fun OptionItem(
    option: Option,
    onDelete: () -> Unit
) {
    ListItem(
        headlineContent = { Text(option.name) },
        supportingContent = { Text("ID: ${option.id}") },
        trailingContent = {
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
            }
        }
    )
}
