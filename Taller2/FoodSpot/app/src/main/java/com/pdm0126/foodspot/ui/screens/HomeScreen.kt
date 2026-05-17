package com.pdm0126.foodspot.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pdm0126.foodspot.data.Restaurant
import com.pdm0126.foodspot.ui.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(vm: HomeViewModel, onRest: (Int) -> Unit, onSearch: () -> Unit, onCart: () -> Unit) {
    val uiState by vm.uiState.collectAsState()
    val cartCount by vm.cartItemCount.collectAsState()

    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = vm.scrollIndex,
        initialFirstVisibleItemScrollOffset = vm.scrollOffset
    )

    LaunchedEffect(listState, uiState.categoriesWithRestaurants.isNotEmpty()) {
        if (uiState.categoriesWithRestaurants.isNotEmpty()) {
            snapshotFlow { listState.firstVisibleItemIndex to listState.firstVisibleItemScrollOffset }
                .distinctUntilChanged()
                .collect { (index, offset) ->
                    vm.scrollIndex = index
                    vm.scrollOffset = offset
                }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("FoodSpot") },
                actions = {
                    IconButton(onClick = onSearch) { Icon(Icons.Default.Search, null) }
                    BadgedBox(
                        badge = { if (cartCount > 0) Badge { Text("$cartCount") } },
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        IconButton(onClick = onCart) { Icon(Icons.Default.ShoppingCart, null) }
                    }
                }
            )
        }
    ) { p ->
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize().padding(p)
        ) {
            uiState.categoriesWithRestaurants.forEach { (cat, rests) ->
                item(key = cat) {
                    Text(cat, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, modifier = Modifier.padding(16.dp))
                }
                item(key = "${cat}_row") {
                    LazyRow(contentPadding = PaddingValues(horizontal = 16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        items(rests, key = { it.id }) { RestaurantCard(it) { onRest(it.id) } }
                    }
                }
            }
        }
    }
}

@Composable
fun RestaurantCard(rest: Restaurant, onClick: () -> Unit) {
    Card(Modifier.width(200.dp).clickable(onClick = onClick), shape = RoundedCornerShape(12.dp)) {
        Column {
            AsyncImage(rest.imageUrl, null, Modifier.fillMaxWidth().height(120.dp).clip(RoundedCornerShape(12.dp, 12.dp)), contentScale = ContentScale.Crop)
            Text(rest.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp))
            Text(rest.description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.secondary, modifier = Modifier.padding(8.dp))
        }
    }
}
