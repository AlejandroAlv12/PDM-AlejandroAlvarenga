package com.pdm0126.foodspot.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pdm0126.foodspot.data.Restaurant
import com.pdm0126.foodspot.ui.viewmodel.SearchViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(vm: SearchViewModel, onRest: (Int) -> Unit, onBack: () -> Unit, onCart: () -> Unit) {
    val uiState by vm.uiState.collectAsState()
    val count by vm.cartItemCount.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextField(
                        value = uiState.query,
                        onValueChange = { vm.onQueryChange(it) },
                        placeholder = { Text("Buscar restaurante o platillo...") },
                        modifier = Modifier.fillMaxWidth(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent
                        ),
                        singleLine = true
                    )
                },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) } },
                actions = {
                    BadgedBox(
                        badge = { if (count > 0) Badge { Text("$count") } },
                        modifier = Modifier.padding(end = 16.dp)
                    ) { IconButton(onClick = onCart) { Icon(Icons.Default.ShoppingCart, null) } }
                }
            )
        }
    ) { p ->
        if (uiState.searchResults.isEmpty() && uiState.hasSearched && uiState.query.isNotEmpty()) {
            Box(Modifier.fillMaxSize().padding(p), Alignment.Center) { Text("No se encontraron resultados") }
        } else {
            LazyColumn(Modifier.fillMaxSize().padding(p), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(uiState.searchResults) { SearchResultCard(it) { onRest(it.id) } }
            }
        }
    }
}

@Composable
fun SearchResultCard(rest: Restaurant, onClick: () -> Unit) {
    Card(Modifier.fillMaxWidth().clickable(onClick = onClick), shape = RoundedCornerShape(12.dp)) {
        Row(Modifier.height(100.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(rest.imageUrl, null, Modifier.width(120.dp).fillMaxHeight().clip(RoundedCornerShape(12.dp, 0.dp, 0.dp, 12.dp)), contentScale = ContentScale.Crop)
            Column(Modifier.padding(12.dp).fillMaxSize(), verticalArrangement = Arrangement.Center) {
                Text(rest.name, style = MaterialTheme.typography.titleMedium)
                Text(rest.categories.joinToString(", "), style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary)
            }
        }
    }
}
