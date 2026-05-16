package com.pdm0126.foodspot.data

data class Dish(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val price: Double
)

data class Restaurant(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val categories: List<String>,
    val menu: List<Dish>
)

data class CartItem(
    val dish: Dish,
    val restaurantName: String,
    var quantity: Int = 1
)
