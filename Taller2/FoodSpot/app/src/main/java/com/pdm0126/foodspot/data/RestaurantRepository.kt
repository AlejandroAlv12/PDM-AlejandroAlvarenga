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
                Dish(4, "Bowl pollo a la naranja", "Pollo empanizado salteado en salsa de naranja con cebolla", "https://admin.donli.com.sv/storage/menu/VlQRUvpUDZRvRONUerBXpZAdGLITDfAEI2D4EPpc.jpg", 6.50),
                Dish(5, "Bowl sopa ramen", "Sopa ramen servida con pollo al wok, maíz dulce, acelga, huevo hervido", "https://admin.donli.com.sv/storage/menu/CyNAI3roRoDIsKhxEK4m10EAuhDngfRGwfIAD3N9.jpg", 7.95),
                Dish(6, "Bowl pollo Kung Pao", "Pollo empanizado salteado en salsa sweet-chili (dulce-picante)", "https://admin.donli.com.sv/storage/menu/djKOqCRAtPlFu5IwtlGL8YW2maqAoq1S8pjpiAmE.jpg", 6.95)
            )
        ),
        Restaurant(
            id = 3,
            name = "McDonald's",
            description = "Hamburguesas rápidas",
            imageUrl = "https://media.biobiochile.cl/wp-content/uploads/2025/08/mcdonalds-hamburgesas-750x400.png",
            categories = listOf("Comida Rápida"),
            menu = listOf(
                Dish(7, "Club House", "2 tortas de carne 100% de res, salsa especial, cebolla caramelizada, tomate, lechuga, tocino y queso cheddar en pan artesanal.", "https://mcdonalds.com.sv/imagen/menu-products/1775500943_JPG%20App_Web%20Express%20700x700%20Digital.jpg", 10.50),
                Dish(8, "Big Mac", "Dos tortas con carne 100% de res con la clásica salsa Big Mac®, lechuga, pepinillos, cebolla picada y queso cheddar amarillo, en pan con ajonjolí.", "https://mcdonalds.com.sv/imagen/menu-products/1766160752_PNG%20Pedidos%20ya%20400x400.png", 7.50),
                Dish(9, "Big Tasty", "Con cebolla, lechuga, dos rodajas de tomate, tres rodajas de queso cheddar blanco y salsa Tasty.", "https://mcdonalds.com.sv/imagen/menu-products/1765832560_PNG%20Pedidos%20ya%20500x500%20%283%29.png", 5.50)
            )
        ),
        Restaurant(
            id = 4,
            name = "Laca Laca",
            description = "Taquería Mexicana",
            imageUrl = "https://laca-laca.com/Content/img/fondoCatMenuCrop.jpg",
            categories = listOf("Comida Rápida"),
            menu = listOf(
                Dish(10, "Los sopes", "Con crema, quesito rallado, cebolla, lechuga y exquisito frijol Laca.", "https://apiapp.laca-laca.com/ServiciosDeliveryCliente/img/grupo/29/16032023095036", 5.99),
                Dish(11, "Los volcanes", "Crujiente tortilla Laca con carne de tu elección y delicioso queso derretido", "https://apiapp.laca-laca.com/ServiciosDeliveryCliente/img/grupo/30/16032023095036", 6.99),
                Dish(12, "El burrito", "Nuestro más fino guacamole, queso y cebollitas refritas en chipotle con mayonesa.", "https://apiapp.laca-laca.com/ServiciosDeliveryCliente/img/grupo/7/02012026014010", 8.25)
            )
        ),
        Restaurant(
            id = 5,
            name = "Starbucks",
            description = "Café y bebidas gourmet",
            imageUrl = "https://scontent.fsal4-1.fna.fbcdn.net/v/t39.30808-6/698417307_1443006267858937_903826801633604786_n.jpg?_nc_cat=104&ccb=1-7&_nc_sid=cc71e4&_nc_eui2=AeFtFxn66GtIYeBIFJrou_Bv9Ft5h97YxIL0W3mH3tjEgkH1ljorWG_c3WGwtmVZjn3byF0oj_wKiQVfBDed7X7o&_nc_ohc=Yxk_X7weei0Q7kNvwExh8lC&_nc_oc=AdrTKc-mXYzK-dBs28NtbDQDd7OsXuqCNzikgaFsakzYf9xEVkPWUvSNTUkVXxffbnc&_nc_zt=23&_nc_ht=scontent.fsal4-1.fna&_nc_gid=Oxjzjk4tAozPKIyZRyiwzA&_nc_ss=7b2a8&oh=00_Af6NFoUvzSQ5aWht8NGrIEliDoTdWpEGaqHmBEwvBp9tkQ&oe=6A0E00F3",
            categories = listOf("Postres"),
            menu = listOf(
                Dish(13, "Bagel de pastrami y crema de aguacate y huevo", "Con semillas relleno de delicioso pastrami, huevo frito y aguacate.", "https://www.starbucks.es/sites/starbucks-es-pwa/files/styles/c10_2_col_text_560x467/public/2024-04/Bagel%20pastrami%20aguacate%20huevo.jpg.webp?h=c8deacc5&itok=MLGpjDXT", 6.00),
                Dish(14, "Roll canela", "Suave masa horneada cada día en nuestras tiendas con canela y una espiral de glaseado.", "https://www.starbucks.es/sites/starbucks-es-pwa/files/styles/c10_2_col_text_560x467/public/2022-03/roll%20canela.png.webp?h=04d92ac6&itok=ffN0w6go", 5.50),
                Dish(15, "Tarta de chocolate", "Crema en su interior y una capa con trozos de bizcocho y glaseado de chocolate.", "https://www.starbucks.es/sites/starbucks-es-pwa/files/styles/c10_2_col_text_560x467/public/2022-03/tarta%20chocolate.png.webp?h=04d92ac6&itok=_ng3SnbF", 9.00)
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
                Dish(19, "Latte", "Espresso con leche cremosa al vapor", "https://fulanos.cafe/assets/latte-n7-E59-y.jpg", 4.00),
                Dish(20, "Croissant", "Croissant de mantequilla recién horneado", "https://fulanos.cafe/assets/croissant-DRZ80wfr.jpg", 3.00),
                Dish(21, "Matcha Latte", "Matcha japonés ceremonial con leche", "https://fulanos.cafe/assets/matcha-BWF0urQ3.jpg", 5.00)
            )
        ),
        Restaurant(
            id = 8,
            name = "Ribs & Bones",
            description = "BBQ Grill",
            imageUrl = "https://lh3.googleusercontent.com/p/AF1QipOH0rJzw7rJoWOMWIAN6-mU5E5DGqB6LRKb_3sL=s1360-w1360-h1020",
            categories = listOf("Comida Americana"),
            menu = listOf(
                Dish(22, "Baby Back Ribs", "Suaves y ahumadas costillas al grill con nuestras salsas BBQ de tu elección acompañadas de papas fritas y ensalada col.", "https://pedidosya.dhmedia.io/image/pedidosya/products/f5e449a7-b9f6-4520-9caa-cb38d52f9f30.jpeg?quality=90&width=1920&webp=1&dpi=1.5", 19.95),
                Dish(23, "New York", "Steak importado, asado a la parrilla servido sobre anillos de cebolla, acompañado de papas gajo, chimichurri, ensalada de col", "https://pedidosya.dhmedia.io/image/pedidosya/products/20ee7baa-abb9-47b1-99e1-acf79add9989.jpeg?quality=90&width=1920&webp=1&dpi=1.5", 17.95),
                Dish(24, "Pollo BBQ", "Pechuga de pollo asada a las brasas adobada en salsa bbq, acompañado por papas fritas y ensalada de col fresca.", "https://pedidosya.dhmedia.io/image/pedidosya/products/82d798b2-df6d-46cf-8d4c-6c150d29db3a.jpeg?quality=90&width=1920&webp=1&dpi=1.5", 10.85)
            )
        ),
        Restaurant(
            id = 9,
            name = "KOI sushi",
            description = "Gastronomía japonesa",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAHNRs0Ly5eTUG7TYMq6vpN7UwTytsLNuPuSNG_L-bg3g-0xIlq18rOPwn6jBtW3tqrYlVtWHJqPq-ytkRRfMQ10lDJoCeD6wvSKbmnXIWOE3lZGaa67MPVJotQG1Mc7NSRfjVt77w=s1360-w1360-h1020",
            categories = listOf("Comida Asiática"),
            menu = listOf(
                Dish(25, "California Roll", "Cangrejo, aguacate, pepino y ajonjolí negro", "https://lh3.googleusercontent.com/gps-cs-s/APNQkAHShO4i_DD5QGmhA7fi0gWAgP3GEXgtTTLXondGIsf3HMwH0m8nASxcFU9dhz8J5tcyeGX2OwlBXdLyUo4jiuaLvYbrVwjaFeXRKFLIAPwZkPUG9gxI9hOI6wZ8V0B4C41Zw9TZ2fXLVQQi=s1360-w1360-h1020", 7.95),
                Dish(26, "Bun Bao Mongolian Beef", "Dorados o al vapor Bao de res acompañado de salsa baozi y kimchi", "https://lh3.googleusercontent.com/gps-cs-s/APNQkAGu21wC60QxKabknUu97sDqrQyS0U3XZX9c4B8wMBSM6MAVOC6dBdv3e53eGeXGmLcAZfLoQ0cesoX-1Qu8-jRseIDaIByObCf3q2-kSsVgFheWfUZV64jx7W1OM4soyPhcrPVqbX1eUzBY=s1360-w1360-h1020", 6.95),
                Dish(27, "Brownie Tempura", "Trozos fde brownie tempurizados, sorbetes de vainilla y topping de fresa", "https://lh3.googleusercontent.com/gps-cs-s/APNQkAHY2RrtOJidLqoSTWrTeK12es-0abc_07stg1uoDbu1ySYQz2yUJ2olquf6qzjF4czVJhgimaJp0WT2XINtl7JnPLq01ZFZanG2oLr5Cj_v6oB6CrpYt8xn0GgGufBr5oisRWkQ=s1360-w1360-h1020", 3.95)
            )
        ),
        Restaurant(
            id = 11,
            name = "Pastaria",
            description = "Cucina casuale italiana",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAFqeQ02wREg-WEpmtHh0LOmmOtknADmjvoM_1SKePRtegvnUJ1wcR1bjnXEU_SN2Ik2gFIxN79OW_HRy4K4iSNm1OP0WO-1m4X_D4ZeYnW2j3xk_Y3H5Iq9grJyfqmxSHnHMmhGrg=s1360-w1360-h1020",
            categories = listOf("Comida Italiana"),
            menu = listOf(
                Dish(31, "Pizza peperoni", "Con peperoni y queso.", "https://lh3.googleusercontent.com/gps-cs-s/APNQkAEacID8yuM9RpSrn9UBKtwI_DI-EgdSY6pgkuHZnHPYp_RGFrVseJUWbm7hT-aldvw-oUjEbQCaR_9RclaV_JHGpzqp9AvyQq9xscEtWpGzGPGoZztV-JMgHKY-ChrkdLaBBVpiKA=s1360-w1360-h1020", 5.99),
                Dish(32, "Fetuccini 3 quesos", "Con salsa de tomate y albahaca.", "https://lh3.googleusercontent.com/gps-cs-s/APNQkAGywnquElxvUrGlxvVicWE4nfrzT864XV2t97NIjU4UGQ7TVoHOiHQF_6bNYUMDCCyCEcEjhq9lUlA-baG6lLlqs2ZcXs2tRmDK45BW4HtasJhlFeMxWfdZrqZCUzkX8M6mFmPw=s1360-w1360-h1020", 7.00),
                Dish(33, "Fetuccini salsa alfredo", "con ostras y salsa alfredo", "https://lh3.googleusercontent.com/gps-cs-s/APNQkAFbghmuoWMKz4XZcErPyAV9JSoH07gQBPT4q6K4oS2TesbWVu92wWjFKLTg71tfteidM-6ChRFxPjszRc9oEaSsoCzcFc_tR0tokjVzt48sYZpL_jW2VVtg-2J_HFsaj528iXvqww=s1360-w1360-h1020", 7.50)
            )
        ),
        Restaurant(
            id = 13,
            name = "KFC",
            description = "El mejor pollo frito",
            imageUrl = "https://www.kfc.com.sv/static/media/images/products/webp/CMB_21487_MD_03-07-2025-09-36-26.webp?2.0.8",
            categories = listOf("Comida Rápida"),
            menu = listOf(
                Dish(37, "Banquete 6 piezas", "6 Piezas de pollo crujiente + 3 piezas de papas fritas.", "https://www.kfc.com.sv/static/media/images/products/webp/CMB_21487_MD_03-07-2025-09-36-26.webp?2.0.8", 16.99),
                Dish(38, "Big Kruncher", "Hamburguesa de pollo krunck + papas fritas.", "https://www.kfc.com.sv/static/media/images/products/webp/CMB_1196_MD_11-03-2026-17-07-06.webp?2.0.8", 6.60),
                Dish(39, "Papas bacon BBQ", "Papas fritas con bacon y BBQ.", "https://www.kfc.com.sv/static/media/images/products/webp/PRD_20944_MD_20-02-2026-11-27-28.webp?2.0.8", 3.25)
            )
        ),
        Restaurant(
            id = 14,
            name = "Petite Bakery",
            description = "Desserts & Coffee",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAE8aEpGJlvWF9ltU_YrE4enPjL3FhAD5vhA3iAjEIpZE1Eb8e5RdzR3eU_yLSCxARvr7LS3EHln7Uwy7R5z2ZBBYDiNaKP7w5Ekch5UZxM30Yl79J6sQW5ONYUlu2aiJIUm30T_rpoZVGc=s1360-w1360-h1020",
            categories = listOf("Postres"),
            menu = listOf(
                Dish(40, "Panini caprese", "Pesto, mozzarella fresca, tomate, albahaca fresca", "https://lh3.googleusercontent.com/p/AF1QipMsz7sqkzEzR-4yifh_PCIG19xcn2r7VCx2VQV3=s1360-w1360-h1020", 6.55),
                Dish(41, "Oreo Cheesecake", "Cheesecake de oreo con crema batida", "https://lh3.googleusercontent.com/p/AF1QipP-wEUbpT1B8110HUiqtDQ_dqpBmNFYt7hvrclC=s1360-w1360-h1020", 9.50),
                Dish(42, "Cheesecake de maracuya", "Cheesecake de maracuya con crema batida", "https://lh3.googleusercontent.com/p/AF1QipMtIC4KzsZkQNmgWXd3xSaBAfIF1lHyPdHWSY_H=s1360-w1360-h1020", 10.00)
            )
        ),
        Restaurant(
            id = 15,
            name = "Bennigan's",
            description = "American fare",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAEoo23Hp86cgwpZvqcfZzfSJjwdU2NHw7c3WO7dIUJxJjK9pmYwMahxnARoAk5GiIuQjEIECZaLjs_YYWUDDqM2u6Nh6ig7IwIQqg2Qq7NeNJriuUtNTqLGtpTIh8TdRgKzt0Rn=s1360-w1360-h1020",
            categories = listOf("Comida Americana"),
            menu = listOf(
                Dish(43, "BBQ Bacon Cheddar Burger", "Cheddar cheese, BBQ sauce, Applewood smoked bacon, and mustard.", "https://lh3.googleusercontent.com/proxy/HYdCB2_imAPteV0aOPXeKLYNlhpI8pVHTzIkRaiJzIqjuhrQMVYpZLazwUbUV8VY70Xe51gwX_ntu8l2r29_KFJMwD6IDWaxOyifX3GyD_25IrnN-S8PT_wyqXdTHjbAtJSyy5W7xoBi-cAiSWaSkc8sALsEPw=s1360-w1360-h1020", 13.50),
                Dish(44, "GUINNESS® Irish Ribs", "Sabrosas costillas ahumadas al estilo Irlandés y cubiertas con glaseado GUINNESS® Stout exclusivo de Bennigan’s. ", "https://lh3.googleusercontent.com/proxy/rp89B3t_GZTdwu-dIlr7UzuhJ666RXsWuo29s5PYC4AcuNRjcYBmhb3xndWkYTGCfGWBd7yMnULsUumeBxyirqnKi11TnfyJu48R3mHA9e5-SOFwfoEVL8kjdEMA0timqqmIbWvEEs1sXlrVs7QqTamgIBieH50=s1360-w1360-h1020", 8.50),
                Dish(45, "Cheeseburger Pub Bites", "Cuatro mini hamburguesas de carne con Queso Americano.", "https://lh3.googleusercontent.com/proxy/WaVDETtEJ2kx8bffNPb0wMslGtKf9MqvvqLgsoq0jMHRZcy465kLXN3dOpp-OiqR1ex7qS37zjpejb-V_Hjf8C5acJw4h_OyCywAkcsgAWDIfo94XM8cFw3EEwJCWwaSXCX9Yg6zm9qNKzfPyTVE_wHIhnaTQg=s1360-w1360-h1020", 10.99)
            )
        ),
        Restaurant(
            id = 16,
            name = "Basilico",
            description = "Italian bistro",
            imageUrl = "https://lh3.googleusercontent.com/gps-cs-s/APNQkAFBSOq0-L2EyRUxciQISOQgYbM6kCJRNpB60UAq66owrv7YwnjCDGd4liViOkk1Yez6F3YqVwuU5zpC3Hz_io3ZYwXnNGNAgqyc-c87kWYbVXYEfB-faFzrK6-d7UpGL4ktStXqpLO1c0XW=s1360-w1360-h1020",
            categories = listOf("Comida Italiana"),
            menu = listOf(
                Dish(46, "Pizza Caprese", "Mozzarella, tomates fresco, pesto de albahaca y reducción balsámica.", "https://lh3.googleusercontent.com/gps-cs-s/APNQkAEOrdN2LD2x20I_fLu3Uj8cVVAvfwujcTFRMOO3GR48gar4YDzNSC5nvYOLp5Ws0tqoBHtawasH8rlfqXsbbTmSbcQnkmM2sOQYbm7_O0gFFNtE93PsFQDcI1T4k0K9uiENAj_OHKI3B9jx=s1360-w1360-h1020", 12.00),
                Dish(47, "Fetuccini alfredo de gamberi", "Fetuccini con salsa alfredo de gamberi", "https://lh3.googleusercontent.com/gps-cs-s/APNQkAFBgUmSV9_JtHwsDz7fKEMc8WlTT9Yj7K1cQFAX3nl4ShbOs5TFPksBVCh5YTTI_uIaBh5ersMSGQqy1AstH9yJ5XtiZdh-h5p96e5ZbyOsJtVMXF54jAFMyGzC1wt_c1n9LJpVqhofYKXK=s1360-w1360-h1020", 14.00),
                Dish(48, "Panna Cotta Al Frutti Di Bosco", "Postre de crema cocida con un coulis de frutos rojos del bosque.", "https://lh3.googleusercontent.com/gps-cs-s/APNQkAHaJcrlkQ54hOz9bf1fl0OtJAndqdGHnH6p4v8xKxz4k1eFnenywUCyg_XX8Drs6Mwkx20PdYGH4YeuuN0IzNzxEvY7NN1GOS1eNQa6PpbECxoyccwuoHYAF0HS-qloK-TRvLy0=s1360-w1360-h1020", 4.00)
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