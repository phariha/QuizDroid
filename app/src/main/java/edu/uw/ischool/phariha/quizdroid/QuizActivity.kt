package edu.uw.ischool.phariha.quizdroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.io.Serializable

class QuizActivity : AppCompatActivity() {
    private var currentQuestionIndex = 0
    private var numCorrectAnswers = 0

    data class QuizQuestion(
        val question: String,
        val options: List<String>,
        val correctAnswer: Int
    ) : Serializable

    private var correctAnswers = 0
    private val mathQuestions = listOf(
        QuizQuestion("What is x if 5x + 5 = 15?", listOf("2", "3", "4", "5"), 1),
        QuizQuestion("What is x if 3x + 5 = 23?", listOf("6", "8", "10", "12"), 0),
        QuizQuestion("What is x if 2x + 4 = 40?", listOf("16", "20", "18", "22"), 2)
    )

    private val physicsQuestions = listOf(
        QuizQuestion("What is the formula for force?", listOf("F = ma", "E = mc^2", "F = mg", "F = mb"), 0),
        QuizQuestion("What is the SI unit of energy?", listOf("Coulomb", "Watt", "Newton", "Joule"), 3),
        QuizQuestion(
            "What is Newton's first law of motion?",
            listOf(
                "F = ma",
                "An object at rest stays at rest",
                "Every action has an equal and opposite reaction",
                "Objects fall at the same rate regardless of mass"
            ),
            1
        )
    )

    private val superheroQuestions = listOf(
        QuizQuestion("Who is the Man of Steel?", listOf("Batman", "Spider-Man", "Superman", "Iron Man"), 2),
        QuizQuestion("What is Batman's real identity?", listOf("Bruce Wayne", "Clark Kent", "Peter Parker", "Tony Stark"), 0),
        QuizQuestion("What is Thor's hammer called?", listOf("Super Soldier Serum", "Arc Reactor", "Gamma radiation", "Mjolnir"), 3)
    )

    private lateinit var questions: List<QuizQuestion>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        val selectedTopic = intent.getStringExtra("selectedTopic")

        questions = when (selectedTopic) {
            "Math" -> mathQuestions
            "Physics" -> physicsQuestions
            "Superheros" -> superheroQuestions
            else -> {
                listOf()
            }
        }
        updateQuestion(questions[0])

        val submitButton = findViewById<Button>(R.id.submitButton)

        submitButton.setOnClickListener {
            val radioGroup = findViewById<RadioGroup>(R.id.radiogroup)
            val selectedRadioButtonId: Int = radioGroup.checkedRadioButtonId
            if (selectedRadioButtonId == -1) {
                return@setOnClickListener
            }

            if (currentQuestionIndex < questions.size) {
                val selectedRadioButton = findViewById<RadioButton>(selectedRadioButtonId)
                val selectedIndex = radioGroup.indexOfChild(selectedRadioButton)
                val correctAnswerIndex = questions[currentQuestionIndex].correctAnswer
                val userAnswer = questions[currentQuestionIndex].options[selectedIndex]

                if (selectedIndex == correctAnswerIndex) {
                    numCorrectAnswers++
                }
                if (currentQuestionIndex == questions.size - 1) {
                    finish()
                }

                val intent = Intent(this, AnswerActivity::class.java).apply {
                    putExtra("correctAnswer", questions[currentQuestionIndex].options[correctAnswerIndex])
                    putExtra("isLastQuestion", currentQuestionIndex == questions.size - 1)
                    putExtra("userAnswer", userAnswer)
                    putExtra("numCorrectAnswers", numCorrectAnswers)
                }

                currentQuestionIndex++
                startActivity(intent)
            }
        }

        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            if (currentQuestionIndex > 0) {
                currentQuestionIndex--
                findViewById<Button>(R.id.submitButton).visibility = View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (currentQuestionIndex < questions.size) {
            updateQuestion(questions[currentQuestionIndex])
        }
    }

    private fun updateQuestion(question: QuizQuestion) {
        val questionText: TextView = findViewById(R.id.question)
        val radioGroup: RadioGroup = findViewById<RadioGroup>(R.id.radiogroup)
        radioGroup.clearCheck()
        questionText.text = question.question
        findViewById<RadioButton>(R.id.one).text = question.options[0]
        findViewById<RadioButton>(R.id.two).text = question.options[1]
        findViewById<RadioButton>(R.id.three).text = question.options[2]
        findViewById<RadioButton>(R.id.four).text = question.options[3]
    }
}
