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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.Room
import com.pdm0126.labo6.data.database.AppDatabase
import com.pdm0126.labo6.data.repository.KtorMassiveVoteRepositoryImpl
import com.pdm0126.labo6.ui.screens.MassiveVoteResultScreen
import com.pdm0126.labo6.ui.screens.MassiveVoteScreen
import com.pdm0126.labo6.ui.screens.MenuScreen
import com.pdm0126.labo6.ui.theme.Labo6Theme
import com.pdm0126.labo6.ui.viewmodel.MassiveVoteViewModel
import com.pdm0126.labo6.ui.viewmodel.MassiveVoteViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var database: AppDatabase
    private lateinit var repository: KtorMassiveVoteRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "rankeuca-database"
        ).build()

        repository = KtorMassiveVoteRepositoryImpl(
            database.questionDao(),
            database.optionDao()
        )

        val viewModel: MassiveVoteViewModel by viewModels {
            MassiveVoteViewModelFactory(repository)
        }

        enableEdgeToEdge()
        setContent {
            Labo6Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = "menu") {
                        composable("menu") {
                            MenuScreen(
                                onNavigateToVote = { apiKey ->
                                    navController.navigate("vote/$apiKey")
                                }
                            )
                        }
                        composable("vote/{apiKey}") { backStackEntry ->
                            val apiKey = backStackEntry.arguments?.getString("apiKey") ?: ""
                            MassiveVoteScreen(
                                viewModel = viewModel,
                                apiKey = apiKey,
                                onNavigateToResults = {
                                    navController.navigate("results/$apiKey") {
                                        popUpTo("menu") { inclusive = false }
                                    }
                                }
                            )
                        }
                        composable("results/{apiKey}") { backStackEntry ->
                            val apiKey = backStackEntry.arguments?.getString("apiKey") ?: ""
                            MassiveVoteResultScreen(
                                viewModel = viewModel,
                                apiKey = apiKey,
                                onNavigateBack = {
                                    navController.popBackStack("menu", inclusive = false)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}