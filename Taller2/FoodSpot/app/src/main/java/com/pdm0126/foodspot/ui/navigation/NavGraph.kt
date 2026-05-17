package com.pdm0126.foodspot.ui.navigation

import androidx.compose.animation.core.*
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.ui.NavDisplay
import com.pdm0126.foodspot.data.CartRepository
import com.pdm0126.foodspot.data.RestaurantRepository
import com.pdm0126.foodspot.ui.screens.*
import com.pdm0126.foodspot.ui.viewmodel.*
import kotlinx.serialization.Serializable

@Serializable data object HomeRoute : NavKey
@Serializable data class DetailRoute(val id: Int) : NavKey
@Serializable data object SearchRoute : NavKey
@Serializable data object CartRoute : NavKey

@Composable
fun FoodSpotNavGraph(resRepo: RestaurantRepository, cartRepo: CartRepository) {
    val factory = remember(resRepo, cartRepo) { FoodSpotViewModelFactory(resRepo, cartRepo) }
    val stack = remember { mutableStateListOf<NavKey>(HomeRoute) }
    val pop = { if (stack.size > 1) stack.removeAt(stack.size - 1) }

    val entryProvider = entryProvider {
        entry<HomeRoute> {
            HomeScreen(viewModel(factory = factory), { stack.add(DetailRoute(it)) }, { stack.add(SearchRoute) }, { stack.add(CartRoute) })
        }
        entry<DetailRoute> { key ->
            val vm: DetailViewModel = viewModel(factory = factory)
            LaunchedEffect(key.id) { vm.loadRestaurant(key.id) }
            DetailScreen(vm, pop, { stack.add(CartRoute) })
        }
        entry<SearchRoute> {
            SearchScreen(viewModel(factory = factory), { stack.add(DetailRoute(it)) }, pop, { stack.add(CartRoute) })
        }
        entry<CartRoute> {
            CartScreen(viewModel(factory = factory), pop)
        }
    }

    val animDuration = 300 
    val slideSpec = tween<IntOffset>(animDuration, easing = LinearEasing)
    val fadeSpec = tween<Float>(animDuration, easing = LinearEasing)

    NavDisplay(
        modifier = Modifier.background(Color.Black),
        entries = rememberDecoratedNavEntries(backStack = stack, entryProvider = entryProvider),
        onBack = pop,
        transitionSpec = {
            slideInHorizontally(slideSpec) { it } togetherWith fadeOut(fadeSpec, targetAlpha = 0.5f)
        },
        popTransitionSpec = {
            fadeIn(fadeSpec, initialAlpha = 0.5f) togetherWith slideOutHorizontally(slideSpec) { it }
        },
        predictivePopTransitionSpec = {
            fadeIn(fadeSpec, initialAlpha = 0.5f) togetherWith slideOutHorizontally(slideSpec) { it }
        }
    )
}

class FoodSpotViewModelFactory(private val r: RestaurantRepository, private val c: CartRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T = when (modelClass) {
        HomeViewModel::class.java -> HomeViewModel(r, c)
        DetailViewModel::class.java -> DetailViewModel(r, c)
        SearchViewModel::class.java -> SearchViewModel(r, c)
        CartViewModel::class.java -> CartViewModel(c)
        else -> error("Unknown VM: $modelClass")
    } as T
}
