package edu.uw.ischool.phariha.quizdroid

import android.util.JsonReader
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader

class InMemoryTopicRepository : TopicRepository {
    private val topics = mutableListOf<Topic>()

    init {
        val filePath = "/data/user/0/edu.uw.ischool.phariha.quizdroid/files/question.json"
        val file = File(filePath)
        if (file.exists()) {
            FileInputStream(file).use { inputStream ->
                JsonReader(InputStreamReader(inputStream)).use { jsonReader ->
                    readTopicsFromJsonFile(jsonReader)
                }
            }
        }
    }

    private fun readTopicsFromJsonFile(jsonReader: JsonReader) {
        jsonReader.beginArray()
        while (jsonReader.hasNext()) {
            jsonReader.beginObject()
            var title = ""
            var shortDescription = ""
            var longDescription = ""
            var questions = emptyList<Quiz>()
            while (jsonReader.hasNext()) {
                when (jsonReader.nextName()) {
                    "title" -> title = jsonReader.nextString()
                    "descr" -> shortDescription = jsonReader.nextString()
                    "descr" -> longDescription = jsonReader.nextString()
                    "answers" -> {
                        questions = readQuestionsArray(jsonReader)
                    }
                }
            }
            val topic = Topic(title, shortDescription, longDescription, questions)
            topics.add(topic)
            Log.i("topicList", topics.toString())
            jsonReader.endObject()
        }
        jsonReader.endArray()
    }

    private fun readQuestionsArray(jsonReader: JsonReader): List<Quiz> {
        val questions = mutableListOf<Quiz>()
        jsonReader.beginArray()
        while (jsonReader.hasNext()) {
            jsonReader.beginObject()
            var question = ""
            var options = emptyList<String>()
            var correctOption = 0
            while (jsonReader.hasNext()) {
                when (jsonReader.nextName()) {
                    "text" -> question = jsonReader.nextString()
                    "answers" -> {
                        options = readOptionsArray(jsonReader)
                    }
                    "answer" -> correctOption = jsonReader.nextInt()
                }
            }
            val quiz = Quiz(question, options, correctOption)
            questions.add(quiz)
            jsonReader.endObject()
        }
        jsonReader.endArray()
        return questions
    }

    private fun readOptionsArray(jsonReader: JsonReader): List<String> {
        val options = mutableListOf<String>()
        jsonReader.beginArray()
        while (jsonReader.hasNext()) {
            options.add(jsonReader.nextString())
        }
        jsonReader.endArray()
        return options
    }

    override fun getTopics(): List<Topic> {
        return topics
    }

    override fun getTopicStrings(): List<String> {
        return topics.map { it.title }
    }

    override fun getTopicByName(title: String): Topic? {
        return topics.find { it.title == title }
    }

    override fun getQuestionsByTopic(title: String): List<Quiz> {
        return topics.find { it.title == title }?.questions ?: emptyList()
    }
}
