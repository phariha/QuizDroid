package edu.uw.ischool.phariha.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {
    companion object {
        lateinit var topicRepository: TopicRepository
            private set
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("QuizApp", "QuizApp is being loaded and run.")
        topicRepository = InMemoryTopicRepository()
    }
}
