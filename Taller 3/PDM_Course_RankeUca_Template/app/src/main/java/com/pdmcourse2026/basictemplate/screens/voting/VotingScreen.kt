package com.pdmcourse2026.basictemplate.screens.voting

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import com.pdmcourse2026.basictemplate.ui.models.PlaceModel
import com.pdmcourse2026.basictemplate.ui.theme.UcaPrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VotingScreen(
    viewModel: VotingViewModel = viewModel(),
    onNavigateToResults: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            if (uiState.places.isNotEmpty()) {
                snackbarHostState.showSnackbar(it)
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                title = { Text("RankeUCA - Votá") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = UcaPrimary,
                    titleContentColor = Color.White
                )
            )
        },
        bottomBar = {
            if (uiState.isVoteSuccessful) {
                Button(
                    onClick = onNavigateToResults,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = UcaPrimary,
                        contentColor = Color.White
                    )
                ) {
                    Text("Ir a resultados")
                }
            }
        }
    ) { innerPadding ->
        PullToRefreshBox(
            isRefreshing = uiState.isLoading && uiState.places.isNotEmpty(),
            onRefresh = { viewModel.loadPlaces() },
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (uiState.isLoading && uiState.places.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            } else if (uiState.error != null && uiState.places.isEmpty()) {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Error: ${uiState.error}",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(16.dp)
                    )
                    Button(
                        onClick = { viewModel.loadPlaces() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = UcaPrimary,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Reintentar")
                    }
                }
            } else {
                LazyColumn(
                    contentPadding = PaddingValues(8.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.places) { place ->
                        PlaceItem(
                            place = place,
                            onVote = { viewModel.vote(place.id) },
                            enabled = !uiState.isVoteSuccessful && !uiState.isLoading
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun PlaceItem(
    place: PlaceModel,
    onVote: () -> Unit,
    enabled: Boolean
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable(enabled = enabled) { onVote() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (place.isSelected) MaterialTheme.colorScheme.secondaryContainer 
                             else MaterialTheme.colorScheme.surface
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp)
        ) {
            AsyncImage(
                model = place.imageUrl,
                contentDescription = place.name,
                modifier = Modifier
                    .size(80.dp)
                    .background(Color.LightGray),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = place.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            if (place.isSelected) {
                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Seleccionado",
                    tint = Color.Green,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}
