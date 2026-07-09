package com.pdm0126.labo6.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.pdm0126.labo6.ui.viewmodel.MassiveVoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MassiveVoteScreen(
    viewModel: MassiveVoteViewModel,
    apiKey: String,
    onNavigateToResults: () -> Unit
) {
    val questions by viewModel.questions.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    val selectedOptions by viewModel.selectedOptions.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    val primaryColor = Color(0xFF3B3285)

    LaunchedEffect(Unit) {
        viewModel.refreshQuestions(apiKey)
    }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.clearError()
        }
    }

    LaunchedEffect(uiState.voteSuccess) {
        if (uiState.voteSuccess) {
            onNavigateToResults()
            viewModel.resetSuccess()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Voto Masivo", color = Color.White, fontWeight = FontWeight.Bold) },
                colors = androidx.compose.material3.TopAppBarDefaults.topAppBarColors(containerColor = primaryColor)
            )
        },
        containerColor = Color.White,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = uiState.isLoading,
            onRefresh = { viewModel.refreshQuestions(apiKey) },
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (questions.isEmpty() && !uiState.isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("No hay preguntas disponibles. Tira hacia abajo para recargar.")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        Text(
                            text = "↓ deslizá para actualizar",
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            color = Color.LightGray,
                            style = MaterialTheme.typography.bodySmall
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Elegí una opción por pregunta",
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Gray,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    items(questions, key = { it.question.id }) { item ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray),
                            shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = item.question.text,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                
                                val currentSelected = selectedOptions[item.question.id]
                                
                                item.options.forEach { option ->
                                    val isSelected = currentSelected == option.id
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .selectable(
                                                selected = isSelected,
                                                onClick = { viewModel.selectOption(item.question.id, option.id) }
                                            )
                                            .padding(vertical = 4.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        RadioButton(
                                            selected = isSelected,
                                            onClick = { viewModel.selectOption(item.question.id, option.id) },
                                            colors = RadioButtonDefaults.colors(
                                                selectedColor = primaryColor,
                                                unselectedColor = Color.Gray
                                            )
                                        )
                                        Text(
                                            text = option.value,
                                            modifier = Modifier.padding(start = 8.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    item {
                        val votableQuestions = questions.filter { it.options.isNotEmpty() }
                        Button(
                            onClick = { viewModel.submitVotes(apiKey) },
                            modifier = Modifier.fillMaxWidth(),
                            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                                containerColor = primaryColor,
                                disabledContainerColor = Color.LightGray
                            ),
                            enabled = selectedOptions.size == votableQuestions.size && votableQuestions.isNotEmpty() && !uiState.isLoading
                        ) {
                            Text("Votar", color = Color.White)
                        }
                    }
                }
            }
        }
    }
}
