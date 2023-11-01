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
        val isCorrect = userAnswer == correctAnswer

        val resultTextView = findViewById<TextView>(R.id.resultTextView)
        resultTextView.text = if (isCorrect) "Correct!" else "Incorrect!"

        val answerTextView = findViewById<TextView>(R.id.answerTextView)
        answerTextView.text = "Your answer: $userAnswer\nCorrect answer: $correctAnswer"

        val scoreTextView = findViewById<TextView>(R.id.scoreTextView)
        val currentScore = intent.getIntExtra("currentScore", 0)
        val totalQuestions = intent.getIntExtra("totalQuestions", 0)

        val updatedScore = if (isCorrect) currentScore + 1 else currentScore

        val scoreText = "You have $updatedScore out of $totalQuestions correct"
        scoreTextView.text = scoreText

        val remainingQuestions = intent.getIntExtra("remainingQuestions", 0)

        val nextButton = findViewById<Button>(R.id.finish)
        nextButton.setOnClickListener {
            if (remainingQuestions > 0) {
                val nextQuestionIntent = Intent(this, QuizActivity::class.java)
                nextQuestionIntent.putExtra("currentScore", updatedScore)
                startActivity(nextQuestionIntent)
                finish()
            } else {
                val mainIntent = Intent(this, MainActivity::class.java)
                startActivity(mainIntent)
                finish()
            }
        }
    }
}

