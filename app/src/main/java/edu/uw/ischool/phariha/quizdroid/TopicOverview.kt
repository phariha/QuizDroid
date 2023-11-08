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

        if (selectedTopic != null) {
            topicTextView.text = "Topic: ${selectedTopic}"

            descriptionTextView.text = "Description: $selectedTopic"

            val beginButton = findViewById<Button>(R.id.btnBegin)
            beginButton.setOnClickListener {
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra("selectedTopic", selectedTopic)
                startActivity(intent)
            }
        } else {
            topicTextView.text = "Topic: Not Found"
            descriptionTextView.text = "Description: Not Found"
        }
    }
}
