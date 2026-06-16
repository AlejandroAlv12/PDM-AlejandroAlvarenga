package com.pdmcourse2026.basictemplate

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdmcourse2026.basictemplate.screens.results.ResultsScreen
import com.pdmcourse2026.basictemplate.screens.results.ResultsViewModel
import com.pdmcourse2026.basictemplate.screens.voting.VotingScreen
import com.pdmcourse2026.basictemplate.screens.voting.VotingViewModel

@Composable
fun RankeUCA_App() {
    val context = LocalContext.current
    val application = context.applicationContext as RankeUCAApplication
    val backStack = rememberNavBackStack(Routes.Voting)

    val votingViewModel: VotingViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                VotingViewModel(repository = application.repository)
            }
        }
    )

    val resultsViewModel: ResultsViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                ResultsViewModel(repository = application.repository)
            }
        }
    )

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = entryProvider {
            entry<Routes.Voting> {
                VotingScreen(
                    viewModel = votingViewModel,
                    onNavigateToResults = {
                        backStack.add(Routes.Results)
                    }
                )
            }
            entry<Routes.Results> {
                ResultsScreen(
                    viewModel = resultsViewModel,
                    onNavigateToVoting = {
                        votingViewModel.resetVote()
                        if (backStack.size > 1) {
                            backStack.removeAt(backStack.lastIndex)
                        }
                    }
                )
            }
        },
    )
}
