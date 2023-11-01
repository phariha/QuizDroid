package edu.uw.ischool.phariha.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TopicOverview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        val selectedTopic = intent.getStringExtra("selected")
        val topicTextView = findViewById<TextView>(R.id.topic)
        val descriptionTextView = findViewById<TextView>(R.id.description)

        topicTextView.text = "Topic: $selectedTopic"

        val description = when (selectedTopic) {
            "Math" -> "This quiz will be on a few short questions about algebra."
            "Physics" -> "This quiz will be on a few short questions about Newton's laws."
            "Superheros" -> "This quiz will be on a few short questions about Marvel and DC Superheros."
            else -> "No description available for $selectedTopic"
        }

        descriptionTextView.text = "Description: $description"

        val beginButton = findViewById<Button>(R.id.btnBegin)
        beginButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("selectedTopic", selectedTopic)
            startActivity(intent)
        }
    }
}
