package edu.uw.ischool.phariha.quizdroid

import android.util.JsonReader


interface TopicRepository {
    fun getTopicTitles(): List<String>
    fun loadTopicsFromJson(filePath: String)
    fun readTopic(reader: JsonReader): Topic
    fun readQuiz(reader: JsonReader): Quiz
    fun getQuestionsByTopic(title: String) : List<Quiz>
}

