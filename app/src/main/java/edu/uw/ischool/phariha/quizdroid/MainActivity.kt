package edu.uw.ischool.phariha.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var listView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var topics = ArrayList<String>()

        topics.add("Math")
        topics.add("Physics")
        topics.add("Superheros")

        listView = findViewById(R.id.listview)
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_2, android.R.id.text1, topics)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val selected = topics[position]
            val intent = Intent(this@MainActivity, TopicOverview::class.java)
            intent.putExtra("selected", selected)
            startActivity(intent)
        }
    }
}
