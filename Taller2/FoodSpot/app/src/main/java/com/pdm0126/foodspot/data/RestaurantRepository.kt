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
            name = "Don Li",
            description = "Asian cuisine",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAGuk_Ghrsu77gyYBbF7SRQ06A6WdR6zGCDrCSybuQAB3cKKADZlWmZlQF6Azsfl7nRE26jLt4oD56PbdbsFf0CYo_1BkG00cOlbt4sO7XW6RrFhbSMLq1Mh2WzgoA2zDbVC074=s1360-w1360-h1020",
            categories = listOf("Comida Asiática"),
            menu = listOf(
                Dish(4, "Salmon Roll", "Salmón fresco y aguacate.", "https://images.unsplash.com/photo-1553621042-f6e147245754?auto=format&fit=crop&w=800&q=80", 11.00),
                Dish(5, "Nigiri de Atún", "Atún rojo sobre arroz avinagrado.", "https://images.unsplash.com/photo-1583623025817-d180a2221d0a?auto=format&fit=crop&w=800&q=80", 5.00),
                Dish(6, "Ramen Tonkotsu", "Sopa de cerdo con fideos y huevo.", "https://images.unsplash.com/photo-1569718212165-3a8278d5f624?auto=format&fit=crop&w=800&q=80", 13.50)
            )
        ),
        Restaurant(
            id = 3,
            name = "McDonald's",
            description = "Hamburguesas rápidas",
            imageUrl = "https://media.biobiochile.cl/wp-content/uploads/2025/08/mcdonalds-hamburgesas-750x400.png",
            categories = listOf("Comida Rápida"),
            menu = listOf(
                Dish(7, "Classic Burger", "Carne angus, queso cheddar y tocino.", "https://images.unsplash.com/photo-1568901346375-23c9450c58cd?auto=format&fit=crop&w=800&q=80", 10.50),
                Dish(8, "BBQ Chicken", "Pollo crujiente con salsa BBQ.", "https://images.unsplash.com/photo-1525164286253-04e68b9d94bb?auto=format&fit=crop&w=800&q=80", 9.50),
                Dish(9, "Papas Trufadas", "Papas fritas con aceite de trufa.", "https://images.unsplash.com/photo-1573080496219-bb080dd4f877?auto=format&fit=crop&w=800&q=80", 5.50)
            )
        ),
        Restaurant(
            id = 4,
            name = "Laca Laca",
            description = "Taquería Mexicana",
            imageUrl = "https://laca-laca.com/Content/img/fondoCatMenuCrop.jpg",
            categories = listOf("Comida Rápida"),
            menu = listOf(
                Dish(10, "Tacos al Pastor", "Cerdo marinado con piña.", "https://images.unsplash.com/photo-1551504734-5ee1c4a1479b?auto=format&fit=crop&w=800&q=80", 8.00),
                Dish(11, "Guacamole", "Aguacate fresco con totopos.", "https://images.unsplash.com/photo-1615870534521-473047046bbd?auto=format&fit=crop&w=800&q=80", 7.00),
                Dish(12, "Enchiladas", "Pollo bañado en salsa verde.", "https://images.unsplash.com/photo-1533614767277-99cd28437321?auto=format&fit=crop&w=800&q=80", 10.00)
            )
        ),
        Restaurant(
            id = 5,
            name = "Starbucks",
            description = "Café y bebidas gourmet",
            imageUrl = "https://scontent.fsal4-1.fna.fbcdn.net/v/t39.30808-6/698417307_1443006267858937_903826801633604786_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=cc71e4&_nc_eui2=AeFtFxn66GtIYeBIFJrou_Bv9Ft5h97YxIL0W3mH3tjEgkH1ljorWG_c3WGwtmVZjn3byF0oj_wKiQVfBDed7X7o&_nc_ohc=Yxk_X7weei0Q7kNvwExh8lC&_nc_oc=AdrTKc-mXYzK-dBs28NtbDQDd7OsXuqCNzikgaFsakzYf9xEVkPWUvSNTUkVXxffbnc&_nc_zt=23&_nc_ht=scontent.fsal4-1.fna&_nc_gid=Oxjzjk4tAozPKIyZRyiwzA&_nc_ss=7b2a8&oh=00_Af6NFoUvzSQ5aWht8NGrIEliDoTdWpEGaqHmBEwvBp9tkQ&oe=6A0E00F3",
            categories = listOf("Postres"),
            menu = listOf(
                Dish(13, "Pastel Chocolate", "Bizcocho húmedo con ganache.", "https://images.unsplash.com/photo-1578985545062-69928b1d9587?auto=format&fit=crop&w=800&q=80", 5.00),
                Dish(14, "Cheesecake", "Base de galleta y crema suave.", "https://images.unsplash.com/photo-1533134242443-d4fd215305ad?auto=format&fit=crop&w=800&q=80", 5.50),
                Dish(15, "Cupcake", "Con frosting de vainilla.", "https://images.unsplash.com/photo-1576618148400-f54bed99fcfd?auto=format&fit=crop&w=800&q=80", 3.00)
            )
        ),
        Restaurant(
            id = 6,
            name = "MONTEROSSO",
            description = "Trattoria moderna",
            imageUrl = "https://static.wixstatic.com/media/1b4d86_7f5df02a29564c58b7cd23d6be6867ef~mv2.jpg/v1/fill/w_1000,h_852,al_c,q_85,enc_avif,quality_auto/1b4d86_7f5df02a29564c58b7cd23d6be6867ef~mv2.jpg",
            categories = listOf("Comida Italiana"),
            menu = listOf(
                Dish(16, "Cozze", "MEJILLONES EN MANTEQUILLA DE VINO BLANCO, AJO Y PEREJIL", "https://static.wixstatic.com/media/1b4d86_d3aa8cdad2804475894700e369a9e160~mv2.jpg/v1/fill/w_800,h_975,al_c,q_85,enc_avif,quality_auto/1b4d86_d3aa8cdad2804475894700e369a9e160~mv2.jpg", 15.00),
                Dish(17, "Risotto Caprese", "CARROZ ARBORIO CREMOSO CON TOMATES Y MOZZARELLA.", "https://static.wixstatic.com/media/9b5899_598fb55c0c394cee8f69b1ae9a2ec149~mv2.jpg/v1/fill/w_950,h_1240,al_c,q_85,usm_0.66_1.00_0.01,enc_avif,quality_auto/9b5899_598fb55c0c394cee8f69b1ae9a2ec149~mv2.jpg", 12.00),
                Dish(18, "Polneta fritta con aioli", "PALITOS DE POLENTA CROCANTE CON CREMOSO DE PARMESANO REGGIANO TRUFADO", "https://static.wixstatic.com/media/1b4d86_3b3c3263319443619ac0dd1641f72e7d~mv2.jpg/v1/fill/w_948,h_1158,al_c,q_85,usm_0.66_1.00_0.01,enc_avif,quality_auto/1b4d86_3b3c3263319443619ac0dd1641f72e7d~mv2.jpg", 9.00)
            )
        ),
        Restaurant(
            id = 7,
            name = "Café Fulanos",
            description = "Roaster & CoffeeBar",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAHWz55eI22-fxhuGuKu-cWt0EMhGRQQ2m2uNP_FVIXa_iXf1NEa13dHruhsJPRjmzehAgfcIz3x7VZZ5CYqXCwwPnjj2QOQ8mviMBMLRWHi5p5uKpasXx3x3k2hWw0kp64nalTh=s1360-w1360-h1020",
            categories = listOf("Postres"),
            menu = listOf(
                Dish(19, "Croissant", "Mantequilla pura y hojaldre.", "https://images.unsplash.com/photo-1555507036-ab1f4038808a?auto=format&fit=crop&w=800&q=80", 3.50),
                Dish(20, "Macarons", "Surtido de sabores franceses.", "https://images.unsplash.com/photo-1569864358642-9d16197022c3?auto=format&fit=crop&w=800&q=80", 8.00),
                Dish(21, "Eclair", "Masa choux rellena de crema.", "https://images.unsplash.com/photo-1621506289937-4834b5ae093b?auto=format&fit=crop&w=800&q=80", 4.50)
            )
        ),
        Restaurant(
            id = 8,
            name = "Ribs & Bones",
            description = "BBQ Grill",
            imageUrl = "https://lh3.googleusercontent.com/p/AF1QipOH0rJzw7rJoWOMWIAN6-mU5E5DGqB6LRKb_3sL=s1360-w1360-h1020",
            categories = listOf("Comida Americana"),
            menu = listOf(
                Dish(22, "Ribeye", "Corte marmoleado de 400g.", "https://images.unsplash.com/photo-1546241072-48010ad28c2c?auto=format&fit=crop&w=800&q=80", 25.00),
                Dish(23, "New York", "Corte magro y jugoso.", "https://images.unsplash.com/photo-1600891964599-f61ba0e24092?auto=format&fit=crop&w=800&q=80", 22.00),
                Dish(24, "Puré", "Puré cremoso con mantequilla.", "https://images.unsplash.com/photo-1518977676601-b53f02ac6d31?auto=format&fit=crop&w=800&q=80", 4.50)
            )
        ),
        Restaurant(
            id = 9,
            name = "KOI sushi",
            description = "Gastronomía japonesa",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAHNRs0Ly5eTUG7TYMq6vpN7UwTytsLNuPuSNG_L-bg3g-0xIlq18rOPwn6jBtW3tqrYlVtWHJqPq-ytkRRfMQ10lDJoCeD6wvSKbmnXIWOE3lZGaa67MPVJotQG1Mc7NSRfjVt77w=s1360-w1360-h1020",
            categories = listOf("Comida Asiática"),
            menu = listOf(
                Dish(25, "Pad Thai", "Fideos de arroz salteados.", "https://images.unsplash.com/photo-1559314809-0d155014e29e?auto=format&fit=crop&w=800&q=80", 12.00),
                Dish(26, "Curry Verde", "Pollo con curry y coco.", "https://images.unsplash.com/photo-1455619452474-d2be8b1e70cd?auto=format&fit=crop&w=800&q=80", 13.00),
                Dish(27, "Mango Sticky", "Arroz dulce con mango.", "https://images.unsplash.com/photo-1562607908-410a8d67a14e?auto=format&fit=crop&w=800&q=80", 7.00)
            )
        ),
        Restaurant(
            id = 10,
            name = "Yorkino's",
            description = "New York cuisine",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAH3zytIhlQmo_E9abaKbGmEhmddTv5OV6hpIixp9cb9QvLKxJq6QAig6Q1Q-nUYAgf4CgFXlmOCyA-H1xKh7c_dhod6qnoDTJ9qY-3U8hUr9h9weaFmaOgml8a2cTsPaxgIq2QU7mDx4xq9=s1360-w1360-h1020",
            categories = listOf("Comida Americana"),
            menu = listOf(
                Dish(28, "Costillas", "Lentamente ahumadas.", "https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=800&q=80", 18.00),
                Dish(29, "Brisket", "Carne suave con 12h de cocción.", "https://images.unsplash.com/photo-1584031036380-3fb6f2d51880?auto=format&fit=crop&w=800&q=80", 20.00),
                Dish(30, "Mac & Cheese", "Pasta con mezcla de quesos.", "https://images.unsplash.com/photo-1543339448-b7cbb4e27da3?auto=format&fit=crop&w=800&q=80", 6.00)
            )
        ),
        Restaurant(
            id = 11,
            name = "Pastaria",
            description = "Cucina casuale italiana",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAFqeQ02wREg-WEpmtHh0LOmmOtknADmjvoM_1SKePRtegvnUJ1wcR1bjnXEU_SN2Ik2gFIxN79OW_HRy4K4iSNm1OP0WO-1m4X_D4ZeYnW2j3xk_Y3H5Iq9grJyfqmxSHnHMmhGrg=s1360-w1360-h1020",
            categories = listOf("Comida Italiana"),
            menu = listOf(
                Dish(31, "Diavola", "Salami picante y tomate.", "https://images.unsplash.com/photo-1534308983496-4fabb1a015ee?auto=format&fit=crop&w=800&q=80", 13.00),
                Dish(32, "Calzone", "Relleno de jamón y queso.", "https://images.unsplash.com/photo-1627308595229-7830a5c91f9f?auto=format&fit=crop&w=800&q=80", 12.00),
                Dish(33, "Focaccia", "Hierbas y aceite de oliva.", "https://images.unsplash.com/photo-1541745537411-b8046dc6d66c?auto=format&fit=crop&w=800&q=80", 5.00)
            )
        ),
        Restaurant(
            id = 12,
            name = "GONG",
            description = "Asian cuisine",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAGhI-v7XHD5blBpHSH4Z-Her-kIu4O66M9eo4dlz6cs6ZKPmKrGdgFdtyWzp-VNKdHebnqRLFe5Cgq0OSLsPiVVlFJy-IZn2Jt9qC-307hnz8ZEDjqroozRc_CXNne1Httcmm4eFYw0Mg5d=s1360-w1360-h1020",
            categories = listOf("Comida Asiática"),
            menu = listOf(
                Dish(34, "Pollo Kung Pao", "Con cacahuates y chiles.", "https://images.unsplash.com/photo-1525755662778-989d0524087e?auto=format&fit=crop&w=800&q=80", 11.50),
                Dish(35, "Chow Mein", "Fideos salteados con vegetales.", "https://images.unsplash.com/photo-1585032226651-759b368d7246?auto=format&fit=crop&w=800&q=80", 9.50),
                Dish(36, "Spring Rolls", "Rollitos de vegetales crujientes.", "https://images.unsplash.com/photo-1544025162-d76694265947?auto=format&fit=crop&w=800&q=80", 4.00)
            )
        ),
        Restaurant(
            id = 13,
            name = "KFC",
            description = "El mejor pollo frito",
            imageUrl = "https://www.kfc.com.sv/static/media/images/products/webp/CMB_21487_MD_03-07-2025-09-36-26.webp?2.0.8",
            categories = listOf("Comida Rápida"),
            menu = listOf(
                Dish(37, "Bucket Mixto", "Piezas de pollo crujiente.", "https://images.unsplash.com/photo-1569058242253-92a9c71f9867?auto=format&fit=crop&w=800&q=80", 15.00),
                Dish(38, "Alitas Picantes", "Bañadas en salsa buffalo.", "https://images.unsplash.com/photo-1527477396000-e27163b481c2?auto=format&fit=crop&w=800&q=80", 10.00),
                Dish(39, "Ensalada Col", "Clásico acompañamiento.", "https://images.unsplash.com/photo-1564671165093-20688ff1fffa?auto=format&fit=crop&w=800&q=80", 3.00)
            )
        ),
        Restaurant(
            id = 14,
            name = "Petite Bakery",
            description = "Desserts & Coffee",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAE8aEpGJlvWF9ltU_YrE4enPjL3FhAD5vhA3iAjEIpZE1Eb8e5RdzR3eU_yLSCxARvr7LS3EHln7Uwy7R5z2ZBBYDiNaKP7w5Ekch5UZxM30Yl79J6sQW5ONYUlu2aiJIUm30T_rpoZVGc=s1360-w1360-h1020",
            categories = listOf("Postres"),
            menu = listOf(
                Dish(40, "Glaseada", "Clásica y suave.", "https://images.unsplash.com/photo-1551024601-bec78aea704b?auto=format&fit=crop&w=800&q=80", 1.50),
                Dish(41, "Rellena Nutella", "Con topping de avellana.", "https://images.unsplash.com/photo-1533930491458-4bb32d3d931b?auto=format&fit=crop&w=800&q=80", 2.50),
                Dish(42, "Caja Surtida", "Mix de 6 sabores.", "https://images.unsplash.com/photo-1551024709-8f23befc6f87?auto=format&fit=crop&w=800&q=80", 10.00)
            )
        ),
        Restaurant(
            id = 15,
            name = "Bennigan's",
            description = "American fare",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAEoo23Hp86cgwpZvqcfZzfSJjwdU2NHw7c3WO7dIUJxJjK9pmYwMahxnARoAk5GiIuQjEIECZaLjs_YYWUDDqM2u6Nh6ig7IwIQqg2Qq7NeNJriuUtNTqLGtpTIh8TdRgKzt0Rn=s1360-w1360-h1020",
            categories = listOf("Comida Americana"),
            menu = listOf(
                Dish(43, "Meatloaf", "Pastel de carne con salsa.", "https://images.unsplash.com/photo-1582234372722-50d7ccc30ebd?auto=format&fit=crop&w=800&q=80", 13.50),
                Dish(44, "Pancakes", "Con jarabe de arce.", "https://images.unsplash.com/photo-1528443049172-0479d045b48b?auto=format&fit=crop&w=800&q=80", 8.50),
                Dish(45, "Milkshake", "Batido de fresa natural.", "https://images.unsplash.com/photo-1579954115545-a95591f28bee?auto=format&fit=crop&w=800&q=80", 5.00)
            )
        ),
        Restaurant(
            id = 16,
            name = "Basilico",
            description = "Italian bistro",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAFBSOq0-L2EyRUxciQISOQgYbM6kCJRNpB60UAq66owrv7YwnjCDGd4liViOkk1Yez6F3YqVwuU5zpC3Hz_io3ZYwXnNGNAgqyc-c87kWYbVXYEfB-faFzrK6-d7UpGL4ktStXqpLO1c0XW=s1360-w1360-h1020",
            categories = listOf("Comida Italiana"),
            menu = listOf(
                Dish(46, "Osso Buco", "Ternera estofada con vino.", "https://images.unsplash.com/photo-1594041680534-e8c8cdebd679?auto=format&fit=crop&w=800&q=80", 24.00),
                Dish(47, "Risotto Hongos", "Arroz cremoso.", "https://images.unsplash.com/photo-1476124369491-e7addf5db371?auto=format&fit=crop&w=800&q=80", 15.00),
                Dish(48, "Panna Cotta", "Postre de nata y bayas.", "https://images.unsplash.com/photo-1488477181946-6428a0291777?auto=format&fit=crop&w=800&q=80", 6.00)
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