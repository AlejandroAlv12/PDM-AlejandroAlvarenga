package com.pdm0126.foodspot.data

interface RestaurantRepository {
    fun getAllRestaurants(): List<Restaurant>
    fun getRestaurantById(id: Int): Restaurant?
    fun searchRestaurants(query: String): List<Restaurant>
}

class HardcodedRestaurantRepository : RestaurantRepository {
    private val restaurants = listOf(
        Restaurant(
            id = 1,
            name = "La Piazza",
            description = "Auténtica comida italiana con ingredientes frescos.",
            imageUrl = "https://images.unsplash.com/photo-1555396273-367ea4eb4db5?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Italiana", "Pizza"),
            menu = listOf(
                Dish(1, "Pizza Margherita", "Tomate, mozzarella y albahaca.", "https://images.unsplash.com/photo-1574071318508-1cdbad80ad38?auto=format&fit=crop&w=800&q=80"),
                Dish(2, "Lasagna Bolognese", "Pasta artesanal con salsa de carne.", "https://images.unsplash.com/photo-1551183053-bf91a1d81141?auto=format&fit=crop&w=800&q=80"),
                Dish(3, "Tiramisú", "Postre clásico de café y mascarpone.", "https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?auto=format&fit=crop&w=800&q=80")
            )
        ),
        Restaurant(
            id = 2,
            name = "Sushi Zen",
            description = "El mejor sushi tradicional en el corazón de la ciudad.",
            imageUrl = "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Asiática", "Sushi"),
            menu = listOf(
                Dish(4, "Salmon Roll", "Salmón fresco y aguacate.", "https://images.unsplash.com/photo-1553621042-f6e147245754?auto=format&fit=crop&w=800&q=80"),
                Dish(5, "Nigiri de Atún", "Atún rojo sobre arroz avinagrado.", "https://images.unsplash.com/photo-1583623025817-d180a2221d0a?auto=format&fit=crop&w=800&q=80"),
                Dish(6, "Ramen Tonkotsu", "Sopa de cerdo con fideos y huevo.", "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?auto=format&fit=crop&w=800&q=80")
            )
        ),
        Restaurant(
            id = 3,
            name = "Burger House",
            description = "Hamburguesas gourmet al carbón.",
            imageUrl = "https://images.unsplash.com/photo-1550547660-d9450f859349?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Americana", "Burgers"),
            menu = listOf(
                Dish(7, "Classic Burger", "Carne angus, queso cheddar y tocino.", "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=800&q=80"),
                Dish(8, "BBQ Chicken Burger", "Pollo crujiente con salsa BBQ.", "https://images.unsplash.com/photo-1525164286253-04e68b9d94bb?auto=format&fit=crop&w=800&q=80"),
                Dish(9, "Papas Trufadas", "Papas fritas con aceite de trufa.", "https://images.unsplash.com/photo-1573080496219-bb080dd4f877?auto=format&fit=crop&w=800&q=80")
            )
        ),
        Restaurant(
            id = 4,
            name = "Mexican Flavor",
            description = "Tacos y especialidades mexicanas auténticas.",
            imageUrl = "https://images.unsplash.com/photo-1565299585323-38d6b0865b47?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Mexicana"),
            menu = listOf(
                Dish(10, "Tacos al Pastor", "Cerdo marinado con piña.", "https://images.unsplash.com/photo-1551504734-5ee1c4a1479b?auto=format&fit=crop&w=800&q=80"),
                Dish(11, "Guacamole", "Aguacate fresco con totopos.", "https://images.unsplash.com/photo-1615870534521-473047046bbd?auto=format&fit=crop&w=800&q=80"),
                Dish(12, "Enchiladas Verdes", "Pollo bañado en salsa de tomatillo.", "https://images.unsplash.com/photo-1533614767277-99cd28437321?auto=format&fit=crop&w=800&q=80")
            )
        ),
        Restaurant(
            id = 5,
            name = "Green Salads",
            description = "Opciones saludables y frescas.",
            imageUrl = "https://images.unsplash.com/photo-1512621776951-a57141f2eefd?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Saludable", "Vegetariana"),
            menu = listOf(
                Dish(13, "Cesar Salad", "Lechuga, crutones y aderezo cesar.", "https://images.unsplash.com/photo-1550304943-4f24f54ddde9?auto=format&fit=crop&w=800&q=80"),
                Dish(14, "Quinoa Bowl", "Quinoa, vegetales y vinagreta.", "https://images.unsplash.com/photo-1543339308-43e59d6b73a6?auto=format&fit=crop&w=800&q=80"),
                Dish(15, "Smoothie Verde", "Espinaca, piña y manzana.", "https://images.unsplash.com/photo-1544145945-f904253d0c7b?auto=format&fit=crop&w=800&q=80")
            )
        ),
        Restaurant(
            id = 6,
            name = "Pasta & Co",
            description = "Pasta fresca hecha a mano diariamente.",
            imageUrl = "https://images.unsplash.com/photo-1473093226795-af9932fe5856?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Italiana"),
            menu = listOf(
                Dish(16, "Carbonara", "Huevo, pecorino y guanciale.", "https://images.unsplash.com/photo-1612874742237-6526221588e3?auto=format&fit=crop&w=800&q=80"),
                Dish(17, "Fettuccine Alfredo", "Crema de mantequilla y parmesano.", "https://images.unsplash.com/photo-1645112481338-3560e9bc041c?auto=format&fit=crop&w=800&q=80"),
                Dish(18, "Ravioli de Espinaca", "Rellenos de ricota y espinaca.", "https://images.unsplash.com/photo-1473093226795-af9932fe5856?auto=format&fit=crop&w=800&q=80")
            )
        ),
        Restaurant(
            id = 7,
            name = "Le Parisien",
            description = "Repostería y cocina francesa fina.",
            imageUrl = "https://images.unsplash.com/photo-1550966841-3ee5ad6070d8?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Francesa", "Postres"),
            menu = listOf(
                Dish(19, "Croissant", "Mantequilla pura y hojaldre.", "https://images.unsplash.com/photo-1555507036-ab1f4038808a?auto=format&fit=crop&w=800&q=80"),
                Dish(20, "Quiche Lorraine", "Tarta de tocino y queso.", "https://images.unsplash.com/photo-1621506289937-4834b5ae093b?auto=format&fit=crop&w=800&q=80"),
                Dish(21, "Macarons", "Surtido de sabores franceses.", "https://images.unsplash.com/photo-1569864358642-9d16197022c3?auto=format&fit=crop&w=800&q=80")
            )
        ),
        Restaurant(
            id = 8,
            name = "Steak House",
            description = "Los mejores cortes de carne a la parrilla.",
            imageUrl = "https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Americana", "Cortes"),
            menu = listOf(
                Dish(22, "Ribeye", "Corte marmoleado de 400g.", "https://images.unsplash.com/photo-1546241072-48010ad28c2c?auto=format&fit=crop&w=800&q=80"),
                Dish(23, "New York Steak", "Corte magro y jugoso.", "https://images.unsplash.com/photo-1600891964599-f61ba0e24092?auto=format&fit=crop&w=800&q=80"),
                Dish(24, "Puré de Papa", "Puré cremoso con mantequilla.", "https://images.unsplash.com/photo-1518977676601-b53f02ac6d31?auto=format&fit=crop&w=800&q=80")
            )
        ),
        Restaurant(
            id = 9,
            name = "Thai Palace",
            description = "Sabor exótico de Tailandia.",
            imageUrl = "https://images.unsplash.com/photo-1559314809-0d155014e29e?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Asiática", "Tailandesa"),
            menu = listOf(
                Dish(25, "Pad Thai", "Fideos de arroz salteados.", "https://images.unsplash.com/photo-1559314809-0d155014e29e?auto=format&fit=crop&w=800&q=80"),
                Dish(26, "Curry Verde", "Pollo con curry y coco.", "https://images.unsplash.com/photo-1455619452474-d2be8b1e70cd?auto=format&fit=crop&w=800&q=80"),
                Dish(27, "Mango Sticky Rice", "Arroz dulce con mango.", "https://images.unsplash.com/photo-1562607908-410a8d67a14e?auto=format&fit=crop&w=800&q=80")
            )
        ),
        Restaurant(
            id = 10,
            name = "Taco Loco",
            description = "Diversión y sabor en cada mordida.",
            imageUrl = "https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Mexicana", "Tacos"),
            menu = listOf(
                Dish(28, "Burrito de Res", "Gran tortilla con frijoles y carne.", "https://images.unsplash.com/photo-1584031036380-3fb6f2d51880?auto=format&fit=crop&w=800&q=80"),
                Dish(29, "Nachos Supreme", "Queso, jalapeños y carne.", "https://images.unsplash.com/photo-1513456852971-30c0b8199d4d?auto=format&fit=crop&w=800&q=80"),
                Dish(30, "Churros", "Con canela y chocolate.", "https://images.unsplash.com/photo-1549405183-5a0224388832?auto=format&fit=crop&w=800&q=80")
            )
        )
    )

    override fun getAllRestaurants(): List<Restaurant> = restaurants

    override fun getRestaurantById(id: Int): Restaurant? = restaurants.find { it.id == id }

    override fun searchRestaurants(query: String): List<Restaurant> {
        if (query.isBlank()) return emptyList()
        val lowerQuery = query.lowercase()
        return restaurants.filter { restaurant ->
            restaurant.name.lowercase().contains(lowerQuery) ||
                    restaurant.menu.any { it.name.lowercase().contains(lowerQuery) }
        }
    }
}
