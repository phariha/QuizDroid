package edu.uw.ischool.phariha.quizdroid

// InMemoryTopicRepository.kt
import android.util.JsonReader
import androidx.preference.PreferenceManager
import java.io.FileReader

class InMemoryTopicRepository : TopicRepository {
    private val topicList = mutableListOf<Topic>()

    init {
        val jsonFilePath = "/data/user/0/edu.uw.ischool.phariha.quizdroid/files/questions.json"
        loadTopicsFromJson(jsonFilePath)
    }

    override fun getQuestionsByTopic(title: String): List<Quiz> {
        return topicList.find { it.title == title }?.questions ?: emptyList()
    }

    override fun getTopicTitles(): List<String> {
        return topicList.map { it.title }
    }

    override fun loadTopicsFromJson(filePath: String) {
        topicList.clear()
        JsonReader(FileReader(filePath)).use { reader ->
            reader.beginArray()
            while (reader.hasNext()) {
                topicList.add(readTopic(reader))
            }
            reader.endArray()
        }
    }

    override fun readTopic(reader: JsonReader): Topic {
        var title = ""
        var description = ""
        val questions = mutableListOf<Quiz>()

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "title" -> title = reader.nextString()
                "desc" -> description = reader.nextString()
                "questions" -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        questions.add(readQuiz(reader))
                    }
                    reader.endArray()
                }
            }
        }
        reader.endObject()
        return Topic(title, description, description, questions)
    }

    override fun readQuiz(reader: JsonReader): Quiz {
        var questionText = ""
        var correctAnswer = -1
        val options = mutableListOf<String>()

        reader.beginObject()
        while (reader.hasNext()) {
            when (reader.nextName()) {
                "text" -> questionText = reader.nextString()
                "answer" -> correctAnswer = reader.nextString().toInt() - 1
                "answers" -> {
                    reader.beginArray()
                    while (reader.hasNext()) {
                        options.add(reader.nextString())
                    }
                    reader.endArray()
                }
            }
        }
        reader.endObject()
        return Quiz(questionText, options, correctAnswer)
    }
}