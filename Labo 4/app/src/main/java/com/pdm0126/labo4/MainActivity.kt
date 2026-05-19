package com.pdm0126.labo4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pdm0126.labo4.ui.screens.TODOScreen
import com.pdm0126.labo4.ui.theme.Labo4Theme
import com.pdm0126.labo4.viewmodel.GeneralViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Labo4Theme {
                val navController = rememberNavController()
                val viewModel: GeneralViewModel = viewModel()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "todo_list",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("todo_list") {
                            TODOScreen(viewModel = viewModel)
                        }
                    }
                }
            }
        }
    }
}
