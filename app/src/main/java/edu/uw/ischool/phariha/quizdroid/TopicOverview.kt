package edu.uw.ischool.phariha.quizdroid

import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TopicOverview : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        val selectedTopic = intent.getStringExtra("selected")
        val topic = findViewById<TextView>(R.id.topic)
        topic.text = "You have chosen $selectedTopic"

    }
}