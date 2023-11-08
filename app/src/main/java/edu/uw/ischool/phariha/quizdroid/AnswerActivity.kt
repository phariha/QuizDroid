package edu.uw.ischool.phariha.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnswerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val userAnswer = intent.getStringExtra("userAnswer")
        val correctAnswer = intent.getStringExtra("correctAnswer")
        val numCorrectAnswers = intent.getIntExtra("numCorrectAnswers", 0)
        val isLastQuestion = intent.getBooleanExtra("isLastQuestion", false)


        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        val userAnswerTextView : TextView = findViewById(R.id.userAnswer)
        val scoreText = findViewById<TextView>(R.id.scoreTextView)

        val correctText = "The correct answer was: $correctAnswer"
        val userAnswerText = "Your answer was: $userAnswer"
        val numCorrectAnswer = "$numCorrectAnswers out of 3 questions correct"
        resultTextView.text = correctText
        userAnswerTextView.text = userAnswerText
        scoreText.text = numCorrectAnswer


        val nextButton = findViewById<Button>(R.id.Next)
        if (isLastQuestion) {
            nextButton.text = "Finish"
        }
        nextButton.setOnClickListener() {
            if (isLastQuestion) {
                val intent = Intent(this, MainActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                finish()
            } else {
                finish()
            }
        }
    }
}

