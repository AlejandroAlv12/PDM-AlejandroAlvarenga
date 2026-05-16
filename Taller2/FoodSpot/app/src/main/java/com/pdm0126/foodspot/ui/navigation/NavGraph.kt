package com.pdm0126.foodspot.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.pdm0126.foodspot.data.CartRepository
import com.pdm0126.foodspot.data.RestaurantRepository
import com.pdm0126.foodspot.ui.screens.CartScreen
import com.pdm0126.foodspot.ui.screens.DetailScreen
import com.pdm0126.foodspot.ui.screens.HomeScreen
import com.pdm0126.foodspot.ui.screens.SearchScreen
import com.pdm0126.foodspot.ui.viewmodel.CartViewModel
import com.pdm0126.foodspot.ui.viewmodel.DetailViewModel
import com.pdm0126.foodspot.ui.viewmodel.HomeViewModel
import com.pdm0126.foodspot.ui.viewmodel.SearchViewModel

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
    object Search : Screen("search")
    object Cart : Screen("cart")
}

class FoodSpotViewModelFactory(
    private val restaurantRepository: RestaurantRepository,
    private val cartRepository: CartRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(restaurantRepository, cartRepository) as T
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(restaurantRepository, cartRepository) as T
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(restaurantRepository, cartRepository) as T
            modelClass.isAssignableFrom(CartViewModel::class.java) -> CartViewModel(cartRepository) as T
            else -> throw IllegalArgumentException()
        }
    }
}

@Composable
fun FoodSpotNavGraph(
    navController: NavHostController,
    restaurantRepository: RestaurantRepository,
    cartRepository: CartRepository
) {
    val factory = FoodSpotViewModelFactory(restaurantRepository, cartRepository)
    NavHost(navController, Screen.Home.route) {
        composable(Screen.Home.route) {
            HomeScreen(
                viewModel = viewModel(factory = factory),
                onRestaurantClick = { navController.navigate(Screen.Detail.createRoute(it)) },
                onSearchClick = { navController.navigate(Screen.Search.route) },
                onCartClick = { navController.navigate(Screen.Cart.route) }
            )
        }
        composable(
            route = Screen.Detail.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val vm: DetailViewModel = viewModel(factory = factory)
            vm.loadRestaurant(id)
            DetailScreen(
                viewModel = vm,
                onBackClick = { navController.popBackStack() },
                onCartClick = { navController.navigate(Screen.Cart.route) }
            )
        }
        composable(Screen.Search.route) {
            SearchScreen(
                viewModel = viewModel(factory = factory),
                onRestaurantClick = { navController.navigate(Screen.Detail.createRoute(it)) },
                onBackClick = { navController.popBackStack() },
                onCartClick = { navController.navigate(Screen.Cart.route) }
            )
        }
        composable(Screen.Cart.route) {
            CartScreen(
                viewModel = viewModel(factory = factory),
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}
