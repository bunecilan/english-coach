package com.englishcoach.app

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import kotlin.math.max

class LearningStore(context: Context) {
    private val prefs = context.getSharedPreferences("english_coach_store", Context.MODE_PRIVATE)

    var selectedLevel: CefrLevel
        get() = runCatching { CefrLevel.valueOf(prefs.getString("level", "A1") ?: "A1") }.getOrDefault(CefrLevel.A1)
        set(value) { prefs.edit().putString("level", value.name).apply() }

    var xp: Int
        get() = prefs.getInt("xp", 0)
        set(value) { prefs.edit().putInt("xp", value).apply() }

    var streak: Int
        get() = prefs.getInt("streak", 0)
        private set(value) { prefs.edit().putInt("streak", value).apply() }

    private fun dayString(offsetDays: Int = 0): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, offsetDays)
        return SimpleDateFormat("yyyy-MM-dd", Locale.US).format(cal.time)
    }

    fun touchDailyStreak() {
        val today = dayString()
        val previous = prefs.getString("last_day", null)
        if (previous == today) return
        streak = if (previous == dayString(-1)) streak + 1 else 1
        prefs.edit().putString("last_day", today).apply()
    }

    fun completeLesson(id: String, topic: String, correct: Int, total: Int) {
        val completed = completedLessons().toMutableSet()
        val first = completed.add(id)
        prefs.edit().putStringSet("completed_lessons", completed).apply()
        if (first) xp += 50
        xp += correct * 8
        recordTopic(topic, correct, total)
        touchDailyStreak()
    }

    fun completedLessons(): Set<String> = prefs.getStringSet("completed_lessons", emptySet()) ?: emptySet()

    fun completeStory(id: String, correct: Int, total: Int) {
        val done = completedStories().toMutableSet()
        val first = done.add(id)
        prefs.edit().putStringSet("completed_stories", done).apply()
        if (first) xp += 35
        xp += correct * 5
        touchDailyStreak()
    }

    fun completedStories(): Set<String> = prefs.getStringSet("completed_stories", emptySet()) ?: emptySet()

    fun toggleFavoriteStory(id: String): Boolean {
        val set = favoriteStories().toMutableSet()
        val now = if (set.contains(id)) { set.remove(id); false } else { set.add(id); true }
        prefs.edit().putStringSet("favorite_stories", set).apply()
        return now
    }

    fun favoriteStories(): Set<String> = prefs.getStringSet("favorite_stories", emptySet()) ?: emptySet()

    fun saveWord(word: String, meaning: String) {
        val set = savedWords().toMutableSet()
        set.add("$word|||$meaning")
        prefs.edit().putStringSet("saved_words", set).apply()
        xp += 2
    }

    fun removeWord(word: String) {
        val set = savedWords().filterNot { it.substringBefore("|||").equals(word, true) }.toSet()
        prefs.edit().putStringSet("saved_words", set).apply()
    }

    fun savedWords(): Set<String> = prefs.getStringSet("saved_words", emptySet()) ?: emptySet()

    fun recordTopic(topic: String, correct: Int, total: Int) {
        if (total <= 0) return
        val safe = topic.replace(" ", "_").lowercase()
        val attempts = prefs.getInt("topic_${safe}_attempts", 0) + total
        val wins = prefs.getInt("topic_${safe}_wins", 0) + correct
        prefs.edit().putInt("topic_${safe}_attempts", attempts).putInt("topic_${safe}_wins", wins).apply()
    }

    fun topicMastery(topic: String): Int {
        val safe = topic.replace(" ", "_").lowercase()
        val attempts = prefs.getInt("topic_${safe}_attempts", 0)
        val wins = prefs.getInt("topic_${safe}_wins", 0)
        return if (attempts == 0) 0 else ((wins * 100f) / attempts).toInt().coerceIn(0, 100)
    }

    fun suggestedDifficulty(topic: String): String {
        val mastery = topicMastery(topic)
        return when {
            mastery == 0 -> "Kolay"
            mastery < 55 -> "Kolay"
            mastery < 80 -> "Orta"
            else -> "Zor"
        }
    }

    fun dailyGoalProgress(): Int {
        val today = dayString()
        return if (prefs.getString("last_day", "") == today) max(1, prefs.getInt("today_actions", 1)) else 0
    }

    fun markAction() {
        val today = dayString()
        val last = prefs.getString("action_day", "")
        val count = if (last == today) prefs.getInt("today_actions", 0) + 1 else 1
        prefs.edit().putString("action_day", today).putInt("today_actions", count).apply()
        touchDailyStreak()
    }
}
