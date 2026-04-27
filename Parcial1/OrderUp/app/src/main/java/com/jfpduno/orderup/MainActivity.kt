package com.jfpduno.orderup

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
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
import com.jfpduno.orderup.ui.theme.OrderUpTheme
import java.util.Locale

val pupusaImg = "https://comedera.com/wp-content/uploads/sites/9/2023/05/Pupusas-de-queso-shutterstock_1803502444.jpg"
val cafeImg = "https://i.blogs.es/139e0f/cafe-americano2/840_560.jpeg"
val chocoImg = "https://cocinaconcoqui.com/wp-content/uploads/2025/12/chocolate-caliente-casero-500x500.jpg"
val cocaImg = "https://d23esi1h40dfmi.cloudfront.net/wp-content/uploads/2025/08/01124509/00732.jpg"

val menu = listOf(
    Producto(1, "Pupusa de queso", 0.75, pupusaImg, TipoProducto.PUPUSA),
    Producto(2, "Pupusa de frijol con queso", 0.75, pupusaImg, TipoProducto.PUPUSA),
    Producto(3, "Pupusa revuelta", 1.00, pupusaImg, TipoProducto.PUPUSA),
    Producto(4, "Pupusa de chicharrón", 1.00, pupusaImg, TipoProducto.PUPUSA),
    Producto(5, "Pupusa de loroco con queso", 1.00, pupusaImg, TipoProducto.PUPUSA),
    Producto(6, "Pupusa de ayote", 0.75, pupusaImg, TipoProducto.PUPUSA),
    Producto(7, "Pupusa de espinaca", 0.85, pupusaImg, TipoProducto.PUPUSA),
    Producto(8, "Pupusa de jalapeño con queso", 1.00, pupusaImg, TipoProducto.PUPUSA),
    Producto(9, "Café", 1.00, cafeImg, TipoProducto.BEBIDA),
    Producto(10, "Chocolate", 1.50, chocoImg, TipoProducto.BEBIDA),
    Producto(11, "Coca-Cola", 1.25, cocaImg, TipoProducto.BEBIDA)
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OrderUpTheme {
                OrderUpApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderUpApp() {
    val context = LocalContext.current
    var orden by remember { mutableStateOf(mapOf<Producto, Int>()) }
    var verOrden by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (verOrden) "Mi Orden" else "OrderUp!") },
                navigationIcon = {
                    if (verOrden) {
                        IconButton(onClick = { verOrden = false }) {
                            Icon(Icons.Default.ArrowBack, null)
                        }
                    }
                },
                actions = {
                    if (!verOrden) {
                        IconButton(onClick = { verOrden = true }) {
                            BadgedBox(
                                badge = {
                                    if (orden.isNotEmpty()) {
                                        Badge { Text("${orden.values.sum()}") }
                                    }
                                }
                            ) {
                                Icon(Icons.Default.ShoppingCart, null)
                            }
                        }
                    }
                }
            )
        }
    ) { p ->
        if (verOrden) {
            Column(Modifier.padding(p).fillMaxSize()) {
                LazyColumn(Modifier.weight(1f)) {
                    items(orden.toList()) { (prod, cant) ->
                        ListItem(
                            headlineContent = { Text(prod.nombre) },
                            supportingContent = {
                                Text("$cant x $${prod.precio} = $${String.format(Locale.US, "%.2f", prod.precio * cant)}")
                            },
                            trailingContent = {
                                IconButton(onClick = { orden = orden - prod }) {
                                    Icon(Icons.Default.Delete, null, tint = MaterialTheme.colorScheme.error)
                                }
                            }
                        )
                        HorizontalDivider()
                    }
                }
                
                val total = orden.entries.sumOf { it.key.precio * it.value }
                Column(Modifier.padding(16.dp)) {
                    Text("Total: $${String.format(Locale.US, "%.2f", total)}", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Button(
                        onClick = {
                            Toast.makeText(context, "Orden confirmada", Toast.LENGTH_SHORT).show()
                            orden = emptyMap()
                            verOrden = false
                        },
                        Modifier.fillMaxWidth().padding(top = 8.dp),
                        enabled = orden.isNotEmpty()
                    ) {
                        Text("Confirmar Orden")
                    }
                }
            }
        } else {
            LazyColumn(Modifier.padding(p).fillMaxSize()) {
                items(menu) { prod ->
                    val cant = orden[prod] ?: 0
                    Row(
                        Modifier.fillMaxWidth().clickable { orden = orden + (prod to cant + 1) }.padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = prod.imagenUrl,
                            contentDescription = null,
                            Modifier
                                .size(64.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.Crop
                        )
                        Column(Modifier.weight(1f).padding(start = 12.dp)) {
                            Text(prod.nombre, fontWeight = FontWeight.Bold)
                            Text("$${prod.precio}")
                        }
                        if (cant > 0) {
                            Badge(containerColor = MaterialTheme.colorScheme.primaryContainer) {
                                Text("$cant", style = MaterialTheme.typography.labelLarge)
                            }
                        }
                    }
                    HorizontalDivider()
                }
            }
        }
    }
}
