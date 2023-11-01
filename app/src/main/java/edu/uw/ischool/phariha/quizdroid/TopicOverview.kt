package edu.uw.ischool.phariha.quizdroid

import android.os.Bundle
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
            "Math" -> "This is a brief description of the Math topic."
            "Physics" -> "This is a brief description of the Physics topic."
            "Superheros" -> "This is a brief description of the Superheros topic."
            else -> "No description available for $selectedTopic"
        }

        descriptionTextView.text = "Description: $description"
    }
}
