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
            name = "Di Lucca",
            description = "Cucina italiana",
            imageUrl = "https://lh3.googleusercontent.com/proxy/B-TLvnQOL7ZAD28g98ldTWXBr8CZYzP30l_1r7nuT2CqIR6seSoPpiZ_9zXX9bSgqmp_B1Rg1Ms5dt_j8cRDkcJ2_wXKJ9iVGSBJRNpct-ipSE94hluR1Mgs5YBCiuJZu5hvM2dg2QAeXCh2mpMN68yEyH6VxA=s1360-w1360-h1020",
            categories = listOf("Comida Italiana"),
            menu = listOf(
                Dish(1, "Pizza Margherita", "Tomate, mozzarella y albahaca.", "https://lh3.googleusercontent.com/gps-cs-s/APNQkAEJO2Ksv2aY66UHpCtxvRBYJS6sRZPmoISooI3eR8w9FaA0PSo-EbMTVLgygL0DMhnRBANlI7_BOkV45m3qtHFzCLBVomBIgjOjYNwDsXn2nG5D17X9cvfd3EWx0XVJNvVMgUY10q3zlKg=s1360-w1360-h1020", 8.95),
                Dish(2, "Fetuccine", "Fetuccine con camarones y salsa", "https://lh3.googleusercontent.com/gps-cs-s/APNQkAGP0RjvwYldO6fKhZYg66DltDVjZ_io812JCm3OCaLH8z2-8EcSTQPxSvg8oBGDaBbNsj8uA8ywTN25sygexS_7NaL4lxPz5X0VoAGlb6GQpX1cIx9PbZfeO-52w5tqIYpg7TX0=s1360-w1360-h1020", 11.95),
                Dish(3, "Tagliatelle", "Pasta con salsa de tomate y albahaca.", "https://lh3.googleusercontent.com/gps-cs-s/APNQkAHQSjZ7TGG_bPYtIsnzST65qwoW48GeIk3v8ddG5Q7mIRGvviEAewFNHiuv2VlGY7YtIpSYSuzT23q55gFW_efSnv2_YTpbfWhKFmxUKrUEUtVa84sWOhpK-0-FUrkboTsQ0_1J=s1360-w1360-h1020", 12.95)
            )
        ),
        Restaurant(
            id = 2,
            name = "Sushi Zen",
            description = "El mejor sushi tradicional en el corazón de la ciudad.",
            imageUrl = "https://images.unsplash.com/photo-1579871494447-9811cf80d66c?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Comida Asiática"),
            menu = listOf(
                Dish(4, "Salmon Roll", "Salmón fresco y aguacate.", "https://images.unsplash.com/photo-1553621042-f6e147245754?auto=format&fit=crop&w=800&q=80", 11.00),
                Dish(5, "Nigiri de Atún", "Atún rojo sobre arroz avinagrado.", "https://images.unsplash.com/photo-1583623025817-d180a2221d0a?auto=format&fit=crop&w=800&q=80", 5.00),
                Dish(6, "Ramen Tonkotsu", "Sopa de cerdo con fideos y huevo.", "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?auto=format&fit=crop&w=800&q=80", 13.50)
            )
        ),
        Restaurant(
            id = 3,
            name = "Burger House",
            description = "Hamburguesas gourmet al carbón.",
            imageUrl = "https://images.unsplash.com/photo-1550547660-d9450f859349?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Comida Rápida"),
            menu = listOf(
                Dish(7, "Classic Burger", "Carne angus, queso cheddar y tocino.", "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=800&q=80", 10.50),
                Dish(8, "BBQ Chicken", "Pollo crujiente con salsa BBQ.", "https://images.unsplash.com/photo-1525164286253-04e68b9d94bb?auto=format&fit=crop&w=800&q=80", 9.50),
                Dish(9, "Papas Trufadas", "Papas fritas con aceite de trufa.", "https://images.unsplash.com/photo-1573080496219-bb080dd4f877?auto=format&fit=crop&w=800&q=80", 5.50)
            )
        ),
        Restaurant(
            id = 4,
            name = "Mexican Flavor",
            description = "Tacos y especialidades mexicanas auténticas.",
            imageUrl = "https://images.unsplash.com/photo-1565299585323-38d6b0865b47?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Comida Rápida"),
            menu = listOf(
                Dish(10, "Tacos al Pastor", "Cerdo marinado con piña.", "https://images.unsplash.com/photo-1551504734-5ee1c4a1479b?auto=format&fit=crop&w=800&q=80", 8.00),
                Dish(11, "Guacamole", "Aguacate fresco con totopos.", "https://images.unsplash.com/photo-1615870534521-473047046bbd?auto=format&fit=crop&w=800&q=80", 7.00),
                Dish(12, "Enchiladas", "Pollo bañado en salsa verde.", "https://images.unsplash.com/photo-1533614767277-99cd28437321?auto=format&fit=crop&w=800&q=80", 10.00)
            )
        ),
        Restaurant(
            id = 5,
            name = "Dulce Deleite",
            description = "Los mejores postres y pasteles de la ciudad.",
            imageUrl = "https://images.unsplash.com/photo-1551024506-0bccd828d307?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Postres"),
            menu = listOf(
                Dish(13, "Pastel Chocolate", "Bizcocho húmedo con ganache.", "https://images.unsplash.com/photo-1578985545062-69928b1d9587?auto=format&fit=crop&w=800&q=80", 5.00),
                Dish(14, "Cheesecake", "Base de galleta y crema suave.", "https://images.unsplash.com/photo-1533134242443-d4fd215305ad?auto=format&fit=crop&w=800&q=80", 5.50),
                Dish(15, "Cupcake", "Con frosting de vainilla.", "https://images.unsplash.com/photo-1576618148400-f54bed99fcfd?auto=format&fit=crop&w=800&q=80", 3.00)
            )
        ),
        Restaurant(
            id = 6,
            name = "Pasta & Co",
            description = "Pasta fresca hecha a mano diariamente.",
            imageUrl = "https://images.unsplash.com/photo-1473093226795-af9932fe5856?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Comida Italiana"),
            menu = listOf(
                Dish(16, "Carbonara", "Huevo, pecorino y guanciale.", "https://images.unsplash.com/photo-1612874742237-6526221588e3?auto=format&fit=crop&w=800&q=80", 13.00),
                Dish(17, "Fettuccine", "Crema de mantequilla y parmesano.", "https://images.unsplash.com/photo-1645112481338-3560e9bc041c?auto=format&fit=crop&w=800&q=80", 12.00),
                Dish(18, "Ravioli", "Rellenos de ricota y espinaca.", "https://images.unsplash.com/photo-1473093226795-af9932fe5856?auto=format&fit=crop&w=800&q=80", 14.50)
            )
        ),
        Restaurant(
            id = 7,
            name = "Le Parisien",
            description = "Repostería y cocina francesa fina.",
            imageUrl = "https://images.unsplash.com/photo-1550966841-3ee5ad6070d8?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Postres"),
            menu = listOf(
                Dish(19, "Croissant", "Mantequilla pura y hojaldre.", "https://images.unsplash.com/photo-1555507036-ab1f4038808a?auto=format&fit=crop&w=800&q=80", 3.50),
                Dish(20, "Macarons", "Surtido de sabores franceses.", "https://images.unsplash.com/photo-1569864358642-9d16197022c3?auto=format&fit=crop&w=800&q=80", 8.00),
                Dish(21, "Eclair", "Masa choux rellena de crema.", "https://images.unsplash.com/photo-1621506289937-4834b5ae093b?auto=format&fit=crop&w=800&q=80", 4.50)
            )
        ),
        Restaurant(
            id = 8,
            name = "Steak House",
            description = "Los mejores cortes de carne a la parrilla.",
            imageUrl = "https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Comida Americana"),
            menu = listOf(
                Dish(22, "Ribeye", "Corte marmoleado de 400g.", "https://images.unsplash.com/photo-1546241072-48010ad28c2c?auto=format&fit=crop&w=800&q=80", 25.00),
                Dish(23, "New York", "Corte magro y jugoso.", "https://images.unsplash.com/photo-1600891964599-f61ba0e24092?auto=format&fit=crop&w=800&q=80", 22.00),
                Dish(24, "Puré", "Puré cremoso con mantequilla.", "https://images.unsplash.com/photo-1518977676601-b53f02ac6d31?auto=format&fit=crop&w=800&q=80", 4.50)
            )
        ),
        Restaurant(
            id = 9,
            name = "Thai Palace",
            description = "Sabor exótico de Tailandia.",
            imageUrl = "https://images.unsplash.com/photo-1559314809-0d155014e29e?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Comida Asiática"),
            menu = listOf(
                Dish(25, "Pad Thai", "Fideos de arroz salteados.", "https://images.unsplash.com/photo-1559314809-0d155014e29e?auto=format&fit=crop&w=800&q=80", 12.00),
                Dish(26, "Curry Verde", "Pollo con curry y coco.", "https://images.unsplash.com/photo-1455619452474-d2be8b1e70cd?auto=format&fit=crop&w=800&q=80", 13.00),
                Dish(27, "Mango Sticky", "Arroz dulce con mango.", "https://images.unsplash.com/photo-1562607908-410a8d67a14e?auto=format&fit=crop&w=800&q=80", 7.00)
            )
        ),
        Restaurant(
            id = 10,
            name = "Texas BBQ",
            description = "Auténtico sabor de Texas.",
            imageUrl = "https://images.unsplash.com/photo-1529193591184-b1d58069ecdd?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Comida Americana"),
            menu = listOf(
                Dish(28, "Costillas", "Lentamente ahumadas.", "https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=800&q=80", 18.00),
                Dish(29, "Brisket", "Carne suave con 12h de cocción.", "https://images.unsplash.com/photo-1584031036380-3fb6f2d51880?auto=format&fit=crop&w=800&q=80", 20.00),
                Dish(30, "Mac & Cheese", "Pasta con mezcla de quesos.", "https://images.unsplash.com/photo-1543339448-b7cbb4e27da3?auto=format&fit=crop&w=800&q=80", 6.00)
            )
        ),
        Restaurant(
            id = 11,
            name = "Pizzeria Napoli",
            description = "Pizzas al horno de leña.",
            imageUrl = "https://images.unsplash.com/photo-1513104890138-7c749659a591?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Comida Italiana"),
            menu = listOf(
                Dish(31, "Diavola", "Salami picante y tomate.", "https://images.unsplash.com/photo-1534308983496-4fabb1a015ee?auto=format&fit=crop&w=800&q=80", 13.00),
                Dish(32, "Calzone", "Relleno de jamón y queso.", "https://images.unsplash.com/photo-1627308595229-7830a5c91f9f?auto=format&fit=crop&w=800&q=80", 12.00),
                Dish(33, "Focaccia", "Hierbas y aceite de oliva.", "https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?auto=format&fit=crop&w=800&q=80", 5.00)
            )
        ),
        Restaurant(
            id = 12,
            name = "Wok Express",
            description = "Comida rápida asiática.",
            imageUrl = "https://images.unsplash.com/photo-1512058560366-cd2427ffbb2a?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Comida Asiática"),
            menu = listOf(
                Dish(34, "Pollo Kung Pao", "Con cacahuates y chiles.", "https://images.unsplash.com/photo-1525755662778-989d0524087e?auto=format&fit=crop&w=800&q=80", 11.50),
                Dish(35, "Chow Mein", "Fideos salteados con vegetales.", "https://images.unsplash.com/photo-1585032226651-759b368d7246?auto=format&fit=crop&w=800&q=80", 9.50),
                Dish(36, "Spring Rolls", "Rollitos de vegetales crujientes.", "https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=800&q=80", 4.00)
            )
        ),
        Restaurant(
            id = 13,
            name = "Chicken Shack",
            description = "El mejor pollo frito.",
            imageUrl = "https://images.unsplash.com/photo-1562967914-608f82629710?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Comida Rápida"),
            menu = listOf(
                Dish(37, "Bucket Mixto", "Piezas de pollo crujiente.", "https://images.unsplash.com/photo-1569058242253-92a9c71f9867?auto=format&fit=crop&w=800&q=80", 15.00),
                Dish(38, "Alitas Picantes", "Bañadas en salsa buffalo.", "https://images.unsplash.com/photo-1527477396000-e27163b481c2?auto=format&fit=crop&w=800&q=80", 10.00),
                Dish(39, "Ensalada Col", "Clásico acompañamiento.", "https://images.unsplash.com/photo-1564671165093-20688ff1fffa?auto=format&fit=crop&w=800&q=80", 3.00)
            )
        ),
        Restaurant(
            id = 14,
            name = "Donut Heaven",
            description = "Donas artesanales.",
            imageUrl = "https://images.unsplash.com/photo-1528975604071-b4dc52a2d18c?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Postres"),
            menu = listOf(
                Dish(40, "Glaseada", "Clásica y suave.", "https://images.unsplash.com/photo-1551024601-bec78aea704b?auto=format&fit=crop&w=800&q=80", 1.50),
                Dish(41, "Rellena Nutella", "Con topping de avellana.", "https://images.unsplash.com/photo-1533930491458-4bb32d3d931b?auto=format&fit=crop&w=800&q=80", 2.50),
                Dish(42, "Caja Surtida", "Mix de 6 sabores.", "https://images.unsplash.com/photo-1551024709-8f23befc6f87?auto=format&fit=crop&w=800&q=80", 10.00)
            )
        ),
        Restaurant(
            id = 15,
            name = "Roadside Grill",
            description = "Cena clásica americana.",
            imageUrl = "https://images.unsplash.com/photo-1466978913421-dad2ebd01d17?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Comida Americana"),
            menu = listOf(
                Dish(43, "Meatloaf", "Pastel de carne con salsa.", "https://images.unsplash.com/photo-1582234372722-50d7ccc30ebd?auto=format&fit=crop&w=800&q=80", 13.50),
                Dish(44, "Pancakes", "Con jarabe de arce.", "https://images.unsplash.com/photo-1528443049172-0479d045b48b?auto=format&fit=crop&w=800&q=80", 8.50),
                Dish(45, "Milkshake", "Batido de fresa natural.", "https://images.unsplash.com/photo-1579954115545-a95591f28bee?auto=format&fit=crop&w=800&q=80", 5.00)
            )
        ),
        Restaurant(
            id = 16,
            name = "Trattoria Bella",
            description = "Cocina rural italiana.",
            imageUrl = "https://images.unsplash.com/photo-1533777857889-4be7c70b33f7?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Comida Italiana"),
            menu = listOf(
                Dish(46, "Osso Buco", "Ternera estofada con vino.", "https://images.unsplash.com/photo-1594041680534-e8c8cdebd679?auto=format&fit=crop&w=800&q=80", 24.00),
                Dish(47, "Risotto Hongos", "Arroz cremoso.", "https://images.unsplash.com/photo-1476124369491-e7addf5db371?auto=format&fit=crop&w=800&q=80", 15.00),
                Dish(48, "Panna Cotta", "Postre de nata y bayas.", "https://images.unsplash.com/photo-1488477181946-6428a0291777?auto=format&fit=crop&w=800&q=80", 6.00)
            )
        ),
        Restaurant(
            id = 17,
            name = "Taco Fiesta",
            description = "Rapidez y mucho picante.",
            imageUrl = "https://images.unsplash.com/photo-1504674900247-0877df9cc836?auto=format&fit=crop&w=800&q=80",
            categories = listOf("Comida Rápida"),
            menu = listOf(
                Dish(49, "Burrito XL", "Cargado de sabor.", "https://images.unsplash.com/photo-1626700051175-6518a4993f47?auto=format&fit=crop&w=800&q=80", 10.50),
                Dish(50, "Nachos Loco", "Extra queso y jalapeños.", "https://images.unsplash.com/photo-1513456852971-30c0b8199d4d?auto=format&fit=crop&w=800&q=80", 9.00),
                Dish(51, "Agua Jamaica", "Refrescante natural.", "https://images.unsplash.com/photo-1495474472287-4d71bcdd2085?auto=format&fit=crop&w=800&q=80", 3.50)
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