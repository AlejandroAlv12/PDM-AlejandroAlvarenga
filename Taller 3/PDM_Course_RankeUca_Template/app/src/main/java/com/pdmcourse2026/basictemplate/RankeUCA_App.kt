package com.pdmcourse2026.basictemplate

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdmcourse2026.basictemplate.screens.options.OptionsScreen
import com.pdmcourse2026.basictemplate.screens.options.OptionsViewModel
import com.pdmcourse2026.basictemplate.screens.results.ResultsScreen
import com.pdmcourse2026.basictemplate.screens.results.ResultsViewModel
import com.pdmcourse2026.basictemplate.screens.voting.VotingScreen
import com.pdmcourse2026.basictemplate.screens.voting.VotingViewModel

@Composable
fun RankeUCA_App() {
    val backStack = rememberNavBackStack(Routes.Voting)

    val votingViewModel: VotingViewModel = viewModel(factory = VotingViewModel.Factory)
    val resultsViewModel: ResultsViewModel = viewModel(factory = ResultsViewModel.Factory)
    val optionsViewModel: OptionsViewModel = viewModel(factory = OptionsViewModel.Factory)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Voting> {
                VotingScreen(
                    viewModel = votingViewModel,
                    onNavigateToResults = { backStack.add(Routes.Results) }
                )
            }
            entry<Routes.Results> {
                ResultsScreen(
                    viewModel = resultsViewModel,
                    onNavigateToVoting = { backStack.removeLastOrNull() }
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
