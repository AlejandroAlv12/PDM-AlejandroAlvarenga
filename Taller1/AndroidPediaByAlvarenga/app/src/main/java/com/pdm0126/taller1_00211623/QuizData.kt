package com.pdm0126.taller1_00211623

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswer: String,
    val funFact: String
)

object QuizData {
    val questions = listOf(
        Question(
            text = "¿Cuál fue el primer dispositivo comercial en usar el sistema operativo Android?",
            options = listOf("HTC Dream", "Nexus One", "Samsung Galaxy", "Motorola Droid"),
            correctAnswer = "HTC Dream",
            funFact = "El HTC Dream fue lanzado en 2008 y también era conocido como T-Mobile G1."
        ),
        Question(
            text = "¿Qué tipo de nombres solía usar Google para las versiones de Android?",
            options = listOf("Animales", "Postres", "Ciudades", "Planetas"),
            correctAnswer = "Postres",
            funFact = "Desde Android 1.5 Cupcake hasta Android 9 Pie, las versiones tenían nombres de dulces en orden alfabético."
        ),
        Question(
            text = "¿En qué versión de Android se introdujo el Modo Oscuro en todo el sistema?",
            options = listOf("Android 8.0", "Android 9.0", "Android 10", "Android 11"),
            correctAnswer = "Android 10",
            funFact = "Android 10 fue la primera versión en eliminar los nombres de postres de forma pública y trajo el modo oscuro oficial."
        )
    )
}
