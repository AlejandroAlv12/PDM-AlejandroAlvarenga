package com.JAAGUNO.labo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.JAAGUNO.labo1.screens.HomeScreen
import com.JAAGUNO.labo1.screens.NameListScreen
import com.JAAGUNO.labo1.screens.SensorScreen
import com.JAAGUNO.labo1.ui.theme.Labo1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Labo1Theme {
                MainNavigation()
            }
        }
    }
}

@Composable
fun MainNavigation() {
    val navigationState = rememberNavigationState(
        startRoute = Route.Home,
        topLevelRoutes = setOf(Route.Home, Route.NameList, Route.Sensors)
    )
    val navigator = remember { Navigator(navigationState) }

    val entryProvider = entryProvider<NavKey> {
        entry<Route.Home> {
            HomeScreen(
                onNavigateToNames = { navigator.navigate(Route.NameList) },
                onNavigateToSensors = { navigator.navigate(Route.Sensors) }
            )
        }
        entry<Route.NameList> {
            NameListScreen(
                onBack = { navigator.goBack() }
            )
        }
        entry<Route.Sensors> {
            SensorScreen(
                onBack = { navigator.goBack() }
            )
        }
    }

    NavDisplay(
        entries = navigationState.toEntries(entryProvider),
        onBack = { navigator.goBack() }
    )
}
