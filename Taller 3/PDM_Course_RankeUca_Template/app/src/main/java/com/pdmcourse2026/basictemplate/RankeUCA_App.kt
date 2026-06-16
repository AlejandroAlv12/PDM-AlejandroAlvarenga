package com.pdmcourse2026.basictemplate

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.pdmcourse2026.basictemplate.screens.results.ResultsScreen
import com.pdmcourse2026.basictemplate.screens.voting.VotingScreen
import com.pdmcourse2026.basictemplate.screens.voting.VotingViewModel

@Composable
fun RankeUCA_App() {
    val backStack = rememberNavBackStack(Routes.Voting)
    val votingViewModel: VotingViewModel = viewModel()

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
