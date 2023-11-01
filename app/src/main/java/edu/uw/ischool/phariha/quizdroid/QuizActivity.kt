package edu.uw.ischool.phariha.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

data class QuizQuestion(
    val question: String,
    val options: Array<String>,
    val correctAnswer: String
)

class QuizActivity : AppCompatActivity() {

    private val mathQuestions = arrayOf(
        QuizQuestion("What is x if 5x + 5 = 15?", arrayOf("2", "3", "4", "5"), "4"),
        QuizQuestion("What is x if 3x + 5 = 23?", arrayOf("6", "8", "10", "12"), "6"),
        QuizQuestion("What is x if 2x + 4 = 40?", arrayOf("16", "18", "20", "22"), "18")
    )

    private val physicsQuestions = arrayOf(
        QuizQuestion("What is the formula for force?", arrayOf("F = ma", "E = mc^2", "F = mg", "F = mb"), "F = ma"),
        QuizQuestion("What is the SI unit of energy?", arrayOf("Joule", "Watt", "Newton", "Coulomb"), "Joule"),
        QuizQuestion(
            "What is Newton's first law of motion?",
            arrayOf(
                "An object at rest stays at rest",
                "F = ma",
                "Every action has an equal and opposite reaction",
                "Objects fall at the same rate regardless of mass"
            ),
            "An object at rest stays at rest"
        )
    )

    private val superheroQuestions = arrayOf(
        QuizQuestion("Who is the Man of Steel?", arrayOf("Batman", "Spider-Man", "Superman", "Iron Man"), "Superman"),
        QuizQuestion("What is Batman's real identity?", arrayOf("Bruce Wayne", "Clark Kent", "Peter Parker", "Tony Stark"), "Bruce Wayne"),
        QuizQuestion("What is Thor's hammer called?", arrayOf("Mjolnir", "Arc Reactor", "Gamma radiation", "Super Soldier Serum"), "Mjolnir")
    )

    private var currentQuestionIndex = 0
    private lateinit var questions: Array<QuizQuestion>
    private lateinit var currentQuestion: QuizQuestion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val questionText = findViewById<TextView>(R.id.question)
        val radioGroup = findViewById<RadioGroup>(R.id.radiogroup)

        val selectedTopic = intent.getStringExtra("selectedTopic")
        setTopic(selectedTopic)

        displayQuestion()

        val nextButton = findViewById<Button>(R.id.nextButton)
        nextButton.setOnClickListener {
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                displayQuestion()
            }
        }

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                displayQuestion()
            }
        }
    }

    private fun setTopic(topic: String?) {
        when (topic) {
            "Math" -> {
                questions = mathQuestions
            }
            "Physics" -> {
                questions = physicsQuestions
            }
            "Superheroes" -> {
                questions = superheroQuestions
            }
            else -> {
                questions = arrayOf(
                    QuizQuestion("No questions available", emptyArray(), "")
                )
            }
        }
    }

    private fun displayQuestion() {
        val questionTextView = findViewById<TextView>(R.id.question)
        val radioGroup = findViewById<RadioGroup>(R.id.radiogroup)

        currentQuestion = questions[currentQuestionIndex]
        questionTextView.text = currentQuestion.question

        val currentOptions = currentQuestion.options
        radioGroup.removeAllViews()

        for (i in currentOptions.indices) {
            val radioButton = RadioButton(this)
            radioButton.text = currentOptions[i]
            radioGroup.addView(radioButton)
        }
    }
}
