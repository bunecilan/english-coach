package com.englishcoach.app

import android.content.Context
import android.content.Intent
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.util.Locale
import kotlin.math.max

class TtsController(context: Context) {
    private var tts: TextToSpeech? = null
    private var ready = false

    init {
        tts = TextToSpeech(context) { status ->
            ready = status == TextToSpeech.SUCCESS
            if (ready) {
                tts?.language = Locale.UK
                tts?.setSpeechRate(0.92f)
            }
        }
    }

    fun speak(text: String, slow: Boolean = false) {
        if (!ready) return
        tts?.setSpeechRate(if (slow) 0.72f else 0.92f)
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "english_coach")
    }

    fun stop() { tts?.stop() }
    fun shutdown() { tts?.stop(); tts?.shutdown() }
}

@Composable
fun rememberTtsController(): TtsController {
    val context = LocalContext.current
    val controller = remember { TtsController(context) }
    DisposableEffect(Unit) { onDispose { controller.shutdown() } }
    return controller
}

fun speechIntent(prompt: String = "Speak in English"): Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH).apply {
    putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
    putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en-US")
    putExtra(RecognizerIntent.EXTRA_PROMPT, prompt)
    putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3)
}

fun pronunciationScore(expected: String, actual: String): Int {
    val a = normalizeSpeech(expected)
    val b = normalizeSpeech(actual)
    if (a.isBlank() || b.isBlank()) return 0
    val d = levenshtein(a, b)
    val maxLen = max(a.length, b.length).coerceAtLeast(1)
    return ((1f - d.toFloat() / maxLen) * 100).toInt().coerceIn(0, 100)
}

private fun normalizeSpeech(s: String): String = s.lowercase(Locale.US)
    .replace(Regex("[^a-z0-9' ]"), "")
    .replace(Regex("\\s+"), " ")
    .trim()

private fun levenshtein(a: String, b: String): Int {
    val prev = IntArray(b.length + 1) { it }
    val cur = IntArray(b.length + 1)
    for (i in 1..a.length) {
        cur[0] = i
        for (j in 1..b.length) {
            val cost = if (a[i - 1] == b[j - 1]) 0 else 1
            cur[j] = minOf(cur[j - 1] + 1, prev[j] + 1, prev[j - 1] + cost)
        }
        for (j in prev.indices) prev[j] = cur[j]
    }
    return prev[b.length]
}
