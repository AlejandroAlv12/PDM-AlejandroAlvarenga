package com.pdm0126.taller1_00211623

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm0126.taller1_00211623.ui.theme.AndroidPediaByAlvarengaTheme

enum class Screen {
    WELCOME, QUIZ, RESULT
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidPediaByAlvarengaTheme {
                AndroidPediaApp()
            }
        }
    }
}

@Composable
fun AndroidPediaApp() {
    var currentScreen by remember { mutableStateOf(Screen.WELCOME) }
    var score by remember { mutableIntStateOf(0) }
    var currentQuestionIndex by remember { mutableIntStateOf(0) }

    val questions = QuizData.questions

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            when (currentScreen) {
                Screen.WELCOME -> WelcomeScreen(
                    onStartQuiz = {
                        score = 0
                        currentQuestionIndex = 0
                        currentScreen = Screen.QUIZ
                    }
                )
                Screen.QUIZ -> QuizScreen(
                    questions = questions,
                    currentIndex = currentQuestionIndex,
                    score = score,
                    onAnswerSelected = { isCorrect ->
                        if (isCorrect) score++
                    },
                    onNextQuestion = {
                        if (currentQuestionIndex < questions.size - 1) {
                            currentQuestionIndex++
                        } else {
                            currentScreen = Screen.RESULT
                        }
                    }
                )
                Screen.RESULT -> ResultScreen(
                    score = score,
                    totalQuestions = questions.size,
                    onRestartQuiz = {
                        currentScreen = Screen.WELCOME
                    }
                )
            }
        }
    }
}

@Composable
fun WelcomeScreen(onStartQuiz: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "AndroidPedia",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "¿Cuánto sabes de Android?",
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "Estudiante: Alejandro Alvarenga",
            fontSize = 16.sp
        )
        Text(
            text = "Carnet: 00211623",
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(onClick = onStartQuiz) {
            Text("Comenzar Quiz")
        }
    }
}

@Composable
fun QuizScreen(
    questions: List<Question>,
    currentIndex: Int,
    score: Int,
    onAnswerSelected: (Boolean) -> Unit,
    onNextQuestion: () -> Unit
) {
    val question = questions[currentIndex]
    var selectedOption by remember(currentIndex) { mutableStateOf<String?>(null) }
    var isAnswered by remember(currentIndex) { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Pregunta ${currentIndex + 1} de ${questions.size}")
            Text(text = "Puntaje: $score / ${questions.size}")
        }
        
        Spacer(modifier = Modifier.height(24.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Text(
                text = question.text,
                modifier = Modifier.padding(24.dp),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        question.options.forEach { option ->
            val buttonColor = when {
                !isAnswered -> MaterialTheme.colorScheme.primaryContainer
                option == question.correctAnswer -> Color.Green
                option == selectedOption -> Color.Red
                else -> MaterialTheme.colorScheme.surfaceVariant
            }

            Button(
                onClick = {
                    if (!isAnswered) {
                        selectedOption = option
                        isAnswered = true
                        onAnswerSelected(option == question.correctAnswer)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor,
                    contentColor = if (isAnswered && (option == question.correctAnswer || option == selectedOption)) Color.Black else MaterialTheme.colorScheme.onPrimaryContainer
                ),
                enabled = !isAnswered
            ) {
                Text(text = option)
            }
        }

        if (isAnswered) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Sabías que: ${question.funFact}",
                modifier = Modifier.padding(8.dp),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onNextQuestion,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = if (currentIndex == questions.size - 1) "Ver Resultado" else "Siguiente")
            }
        }
    }
}

@Composable
fun ResultScreen(score: Int, totalQuestions: Int, onRestartQuiz: () -> Unit) {
    val message = when (score) {
        totalQuestions -> "¡Excelente! Eres un experto en Android."
        in 1 until totalQuestions -> "¡Buen trabajo! Pero puedes mejorar."
        else -> "Parece que necesitas estudiar más sobre Android."
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Resultado Final",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Obtuviste $score de $totalQuestions",
            fontSize = 22.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = message,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(onClick = onRestartQuiz) {
            Text("Reiniciar Quiz")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AndroidPediaPreview() {
    AndroidPediaByAlvarengaTheme {
        AndroidPediaApp()
    }
}
