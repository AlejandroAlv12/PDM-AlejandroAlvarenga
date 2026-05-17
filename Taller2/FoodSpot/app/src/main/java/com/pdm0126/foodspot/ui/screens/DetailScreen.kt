package com.pdm0126.foodspot.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import java.util.Locale
import com.pdm0126.foodspot.data.Dish
import com.pdm0126.foodspot.ui.viewmodel.DetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(vm: DetailViewModel, onBack: () -> Unit, onCart: () -> Unit) {
    val uiState by vm.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(uiState.restaurant?.name ?: "Detalle") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) } },
                actions = {
                    val count by vm.cartItemCount.collectAsState()
                    BadgedBox(
                        badge = { if (count > 0) Badge { Text("$count") } },
                        modifier = Modifier.padding(end = 16.dp)
                    ) { IconButton(onClick = onCart) { Icon(Icons.Default.ShoppingCart, null) } }
                }
            )
        }
    ) { p ->
        uiState.restaurant?.let { rest ->
            LazyColumn(Modifier.fillMaxSize().padding(p)) {
                item { AsyncImage(rest.imageUrl, null, Modifier.fillMaxWidth().height(200.dp), contentScale = ContentScale.Crop) }
                item {
                    Column(Modifier.padding(16.dp)) {
                        Text(rest.name, style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
                        Text(rest.description, style = MaterialTheme.typography.bodyLarge)
                        Spacer(Modifier.height(16.dp))
                        Text("Menú", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    }
                }
                items(rest.menu) { dish ->
                    DishItem(dish) {
                        vm.addToCart(dish)
                        Toast.makeText(context, "${dish.name} agregado", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        } ?: if (uiState.isLoading) {
            Box(Modifier.fillMaxSize(), Alignment.Center) { CircularProgressIndicator() }
        } else {
            Box(Modifier.fillMaxSize(), Alignment.Center) { Text("No se pudo cargar el restaurante") }
        }
    }
}

@Composable
fun DishItem(dish: Dish, onAdd: () -> Unit) {
    Card(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 6.dp), shape = RoundedCornerShape(12.dp)) {
        Row(Modifier.padding(12.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(dish.imageUrl, null, Modifier.size(85.dp).clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.Crop)
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(dish.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(dish.description, style = MaterialTheme.typography.bodySmall, maxLines = 2, color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(8.dp))
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Text("$${String.format(Locale.US, "%.2f", dish.price)}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.ExtraBold, color = MaterialTheme.colorScheme.primary)
                    Button(onClick = onAdd, contentPadding = PaddingValues(horizontal = 12.dp), modifier = Modifier.height(32.dp), shape = RoundedCornerShape(16.dp)) {
                        Icon(Icons.Default.Add, null, Modifier.size(16.dp))
                        Spacer(Modifier.width(4.dp))
                        Text("Add", style = MaterialTheme.typography.labelLarge)
                    }
                }
            }
        }
    }
}
