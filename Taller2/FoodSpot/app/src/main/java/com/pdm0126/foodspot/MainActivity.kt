package com.pdm0126.foodspot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.pdm0126.foodspot.data.HardcodedRestaurantRepository
import com.pdm0126.foodspot.data.MemoryCartRepository
import com.pdm0126.foodspot.ui.navigation.FoodSpotNavGraph
import com.pdm0126.foodspot.ui.theme.FoodSpotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val restaurantRepository = HardcodedRestaurantRepository()
        val cartRepository = MemoryCartRepository()
        setContent {
            FoodSpotTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    FoodSpotNavGraph(
                        navController = navController,
                        restaurantRepository = restaurantRepository,
                        cartRepository = cartRepository
                    )
                }
            }
        }
    }
}
