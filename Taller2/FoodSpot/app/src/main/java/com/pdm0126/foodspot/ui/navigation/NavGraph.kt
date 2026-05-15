package com.pdm0126.foodspot.ui.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pdm0126.foodspot.data.RestaurantRepository
import com.pdm0126.foodspot.ui.screens.DetailScreen
import com.pdm0126.foodspot.ui.screens.HomeScreen
import com.pdm0126.foodspot.ui.screens.SearchScreen
import com.pdm0126.foodspot.ui.viewmodel.DetailViewModel
import com.pdm0126.foodspot.ui.viewmodel.HomeViewModel
import com.pdm0126.foodspot.ui.viewmodel.SearchViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{restaurantId}") {
        fun createRoute(id: Int) = "detail/$id"
    }
    object Search : Screen("search")
}

class FoodSpotViewModelFactory(private val repository: RestaurantRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(repository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(repository) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}

@Composable
fun FoodSpotNavGraph(
    navController: NavHostController,
    repository: RestaurantRepository
) {
    val factory = FoodSpotViewModelFactory(repository)
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            val viewModel: HomeViewModel = viewModel(factory = factory)
            HomeScreen(
                viewModel = viewModel,
                onRestaurantClick = { id ->
                    navController.navigate(Screen.Detail.createRoute(id))
                },
                onSearchClick = {
                    navController.navigate(Screen.Search.route)
                }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("restaurantId") { type = NavType.IntType })
        ) { backStackEntry ->
            val restaurantId = backStackEntry.arguments?.getInt("restaurantId") ?: 0
            val viewModel: DetailViewModel = viewModel(factory = factory)
            viewModel.loadRestaurant(restaurantId)
            DetailScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(Screen.Search.route) {
            val viewModel: SearchViewModel = viewModel(factory = factory)
            SearchScreen(
                viewModel = viewModel,
                onRestaurantClick = { id ->
                    navController.navigate(Screen.Detail.createRoute(id))
                },
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
