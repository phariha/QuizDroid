package edu.uw.ischool.phariha.quizdroid

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("path", filesDir.toString())

        val serviceIntent = Intent(this, BackgroundService::class.java)
        startService(serviceIntent)

        val topics = QuizApp.topicRepository.getTopicTitles()
        val titleText = findViewById<TextView>(R.id.titleText)
        titleText.text = "Quizdroid"


        listView = findViewById(R.id.listview)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, topics)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selected = topics[position]
            val intent = Intent(this, TopicOverview::class.java)
            intent.putExtra("selected", selected)
            startActivity(intent)
        }
    }
}
