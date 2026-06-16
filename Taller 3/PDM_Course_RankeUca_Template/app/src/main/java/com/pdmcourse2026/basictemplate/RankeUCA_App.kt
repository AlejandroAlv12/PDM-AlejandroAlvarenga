package com.pdmcourse2026.basictemplate

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdmcourse2026.basictemplate.screens.options.OptionsScreen
import com.pdmcourse2026.basictemplate.screens.options.OptionsViewModel

@Composable
fun RankeUCA_App() {
    val context = LocalContext.current
    val application = context.applicationContext as BasicTemplateApplication
    val backStack = rememberNavBackStack(Routes.Voting)

    // Inyectamos el ViewModel usando la Factory definida en el Paso 4
    val optionsViewModel: OptionsViewModel = viewModel(factory = OptionsViewModel.Factory)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Voting> {
                // Por ahora navegamos directamente a Options para probar el Paso 4
                OptionsScreen(
                    viewModel = optionsViewModel,
                    onBack = { /* Manejar atrás */ }
                )
            }
            entry<Routes.Options> {
                OptionsScreen(
                    viewModel = optionsViewModel,
                    onBack = { backStack.removeLastOrNull() }
                )
            }
        },
    )
}
