package edu.uw.ischool.phariha.quizdroid

import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private val questions = arrayOf(
        "Question 1: What is 5x + 5 = 15?",
        "Question 2: What is 5x + 8?",
        "Question 3: What is 5x + 9?"
    )

    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val questionTextView = findViewById<TextView>(R.id.question)
        val radioGroup = findViewById<RadioGroup>(R.id.radiogroup)

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

    private fun displayQuestion() {
        val questionTextView = findViewById<TextView>(R.id.question)
        val radioGroup = findViewById<RadioGroup>(R.id.radiogroup)

        questionTextView.text = questions[currentQuestionIndex]

        val options = arrayOf("3", "2", "5", "12")

        radioGroup.removeAllViews()

        for (i in options.indices) {
            val radioButton = RadioButton(this)
            radioButton.text = options[i]
            radioGroup.addView(radioButton)
        }
    }
}