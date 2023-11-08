package edu.uw.ischool.phariha.quizdroid


class InMemoryTopicRepository : TopicRepository {
    private val topics = mutableListOf<Topic>()

    init {
        topics.add(
            Topic(
                "Math",
                "Math Quiz",
                "This quiz will contain a few short questions about algebra.",
                listOf(
                    Quiz("What is x if 5x + 5 = 15?", listOf("2", "3", "4", "5"), 1),
                    Quiz("What is x if 3x + 5 = 23?", listOf("6", "8", "10", "12"), 0),
                    Quiz("What is x if 2x + 4 = 40?", listOf("16", "20", "18", "22"), 2)
                )
            )
        )

        topics.add(
            Topic(
                "Physics",
                "Physics Quiz",
                "This quiz will contain a few short questions about laws of physics.",
                listOf(
                    Quiz("What is the formula for force?", listOf("F = ma", "E = mc^2", "F = mg", "F = mb"), 0),
                    Quiz("What is the SI unit of energy?", listOf("Coulomb", "Watt", "Newton", "Joule"), 3),
                    Quiz(
                        "What is Newton's first law of motion?",
                        listOf(
                            "F = ma",
                            "An object at rest stays at rest",
                            "Every action has an equal and opposite reaction",
                            "Objects fall at the same rate regardless of mass"
                        ),
                        0
                    )
                )
            )
        )

        topics.add(
            Topic(
                "Superheros",
                "Superheros Quiz",
                "This quiz will contain a few short questions about Marvel and DC Superheros.",
                listOf(
                    Quiz("Who is the Man of Steel?", listOf("Batman", "Spider-Man", "Superman", "Iron Man"), 2),
                    Quiz("What is Batman's real identity?", listOf("Bruce Wayne", "Clark Kent", "Peter Parker", "Tony Stark"), 0),
                    Quiz("What is Thor's hammer called?", listOf("Super Soldier Serum", "Arc Reactor", "Gamma radiation", "Mjolnir"), 3)
                )
            )
        )
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
