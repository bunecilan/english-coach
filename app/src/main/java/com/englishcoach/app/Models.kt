package com.englishcoach.app

enum class CefrLevel(val label: String, val tr: String, val accent: String) {
    A1("A1", "Başlangıç", "Temel ifadeler ve günlük ihtiyaçlar"),
    A2("A2", "Temel", "Günlük konuşmaları büyütme"),
    B1("B1", "Orta", "Bağımsız iletişim ve akıcı sohbet"),
    B2("B2", "Orta-İleri", "Doğal, detaylı ve rahat iletişim"),
    C1("C1", "İleri", "Nüanslı, akademik ve profesyonel İngilizce"),
    C2("C2", "Ustalık", "Çok doğal, esnek ve incelikli kullanım")
}

enum class SkillType(val title: String, val emoji: String) {
    VOCAB("Kelime", "🧠"),
    GRAMMAR("Gramer", "🧩"),
    LISTENING("Dinleme", "🎧"),
    SPEAKING("Konuşma", "🎤"),
    READING("Okuma", "📖"),
    WRITING("Yazma", "✍️")
}

data class Lesson(
    val id: String,
    val level: CefrLevel,
    val order: Int,
    val title: String,
    val subtitle: String,
    val skill: SkillType,
    val explanation: String,
    val simpleExplanation: String,
    val examples: List<ExampleLine>,
    val vocabulary: List<VocabItem>,
    val practice: List<PracticeQuestion>
)

data class ExampleLine(val english: String, val turkish: String, val note: String = "")

data class VocabItem(
    val word: String,
    val meaning: String,
    val example: String,
    val exampleTr: String,
    val pronunciation: String = ""
)

data class PracticeQuestion(
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String,
    val topic: String
)

data class Story(
    val id: String,
    val level: CefrLevel,
    val title: String,
    val category: String,
    val emoji: String,
    val description: String,
    val sentences: List<StorySentence>,
    val vocabulary: List<VocabItem>,
    val questions: List<PracticeQuestion>
)

data class StorySentence(val english: String, val turkish: String)

data class YdsTactic(
    val title: String,
    val icon: String,
    val summary: String,
    val details: List<String>,
    val patterns: List<String>
)

data class SpeakingScenario(
    val level: CefrLevel,
    val title: String,
    val context: String,
    val prompts: List<String>,
    val modelAnswers: List<String>
)

data class Badge(val id: String, val icon: String, val title: String, val description: String)
