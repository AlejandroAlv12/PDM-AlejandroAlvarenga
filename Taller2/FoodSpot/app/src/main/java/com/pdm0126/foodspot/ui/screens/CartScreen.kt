package com.pdm0126.foodspot.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
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
import com.pdm0126.foodspot.data.CartItem
import com.pdm0126.foodspot.ui.viewmodel.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(vm: CartViewModel, onBack: () -> Unit) {
    val uiState by vm.uiState.collectAsState()
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Carrito") },
                navigationIcon = { IconButton(onClick = onBack) { Icon(Icons.AutoMirrored.Filled.ArrowBack, null) } },
                actions = {
                    if (uiState.items.isNotEmpty()) {
                        IconButton(onClick = { vm.clearCart() }) { Icon(Icons.Default.Delete, null) }
                    }
                }
            )
        }
    ) { p ->
        if (uiState.items.isEmpty()) {
            Box(Modifier.fillMaxSize(), Alignment.Center) { Text("Tu carrito está vacío", style = MaterialTheme.typography.titleMedium) }
        } else {
            Column(Modifier.fillMaxSize().padding(p)) {
                LazyColumn(Modifier.weight(1f)) {
                    items(uiState.items) { item -> CartItemRow(item) { vm.removeItem(item.dish.id) } }
                }
                Card(Modifier.fillMaxWidth().padding(16.dp), elevation = CardDefaults.cardElevation(8.dp)) {
                    Column(Modifier.padding(16.dp)) {
                        Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                            Text("Total:", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                            Text("$${String.format("%.2f", uiState.total)}", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                        }
                        Button(
                            onClick = {
                                vm.clearCart()
                                Toast.makeText(context, "Pedido realizado con éxito", Toast.LENGTH_LONG).show()
                                onBack()
                            },
                            modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
                        ) { Text("PAGAR") }
                    }
                }
            }
        }
    }
}

@Composable
fun CartItemRow(item: CartItem, onRemove: () -> Unit) {
    Card(Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(Modifier.padding(8.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(item.dish.imageUrl, null, Modifier.size(60.dp).clip(RoundedCornerShape(8.dp)), contentScale = ContentScale.Crop)
            Spacer(Modifier.width(16.dp))
            Column(Modifier.weight(1f)) {
                Text(item.dish.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text(item.restaurantName, style = MaterialTheme.typography.bodySmall)
                Text("$${String.format("%.2f", item.dish.price)} x ${item.quantity}", style = MaterialTheme.typography.bodyMedium)
                Text("Subtotal: $${String.format("%.2f", item.dish.price * item.quantity)}", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
            }
            IconButton(onClick = onRemove) { Icon(Icons.Default.Delete, null, tint = MaterialTheme.colorScheme.error) }
        }
    }
}
