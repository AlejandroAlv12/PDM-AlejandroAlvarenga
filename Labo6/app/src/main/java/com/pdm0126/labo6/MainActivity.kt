package com.pdm0126.labo6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.pdm0126.labo6.ui.screens.MealsScreen
import com.pdm0126.labo6.ui.theme.Labo6Theme
import com.pdm0126.labo6.ui.viewmodel.MealsViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MealsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Labo6Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MealsScreen(viewModel = viewModel)
                }
            }
        }
    }
}