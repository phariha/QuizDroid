package edu.uw.ischool.phariha.quizdroid


interface TopicRepository {
    fun getTopics(): List<Topic>
    fun getTopicByName(title: String): Topic?
    fun getTopicStrings(): List<String>
    fun getQuestionsByTopic(title: String) : List<Quiz>
}

