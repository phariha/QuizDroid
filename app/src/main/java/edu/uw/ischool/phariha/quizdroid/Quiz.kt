package edu.uw.ischool.phariha.quizdroid

data class Quiz(
    val questionText: String,
    val options: List<String>,
    val correctAnswerIndex: Int
)