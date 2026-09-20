package com.englishcoach.app

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LessonDetailScreen(
    lesson: Lesson,
    store: LearningStore,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onChanged: () -> Unit
) {
    val tts = rememberTtsController()
    var simpler by remember { mutableStateOf(false) }
    var selected by remember { mutableIntStateOf(-1) }
    var checked by remember { mutableStateOf(false) }
    val q = lesson.practice.first()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp, 12.dp, 18.dp, 30.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Geri") }
                Column(Modifier.weight(1f)) {
                    Text("${lesson.level.label} • ${lesson.skill.emoji} ${lesson.skill.title}", color = levelColor(lesson.level), fontWeight = FontWeight.Bold)
                    Text(lesson.title, fontSize = 27.sp, fontWeight = FontWeight.Black)
                }
            }
        }
        item {
            Surface(shape = CardShape, color = Panel) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("Konu", fontWeight = FontWeight.ExtraBold, fontSize = 19.sp)
                    Text(if (simpler) lesson.simpleExplanation else lesson.explanation, color = Color.White.copy(alpha=.9f), lineHeight = 23.sp)
                    TextButton(onClick = { simpler = !simpler }) {
                        Icon(Icons.Default.Lightbulb, null)
                        Spacer(Modifier.width(6.dp))
                        Text(if (simpler) "Normal anlatımı göster" else "Bunu daha da basit anlat")
                    }
                }
            }
        }
        item { Text("Örneklerle gör", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold) }
        items(lesson.examples.size) { i ->
            val ex = lesson.examples[i]
            Surface(shape = SmallShape, color = Panel) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(7.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(ex.english, fontWeight = FontWeight.Bold, fontSize = 17.sp, modifier = Modifier.weight(1f))
                        IconButton(onClick = { tts.speak(ex.english) }) { Icon(Icons.Default.VolumeUp, "Dinle", tint = Mint) }
                        IconButton(onClick = { tts.speak(ex.english, slow = true) }) { Icon(Icons.Default.SlowMotionVideo, "Yavaş dinle", tint = Sky) }
                    }
                    Text(ex.turkish, color = SoftText)
                }
            }
        }
        item { Text("Kelime kutusu", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold) }
        items(lesson.vocabulary.size) { i ->
            val item = lesson.vocabulary[i]
            Surface(shape = SmallShape, color = Panel) {
                Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(item.word, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp)
                        Text(item.meaning, color = levelColor(lesson.level))
                    }
                    IconButton(onClick = { tts.speak(item.word) }) { Icon(Icons.Default.VolumeUp, "Dinle") }
                    IconButton(onClick = { store.saveWord(item.word, item.meaning); onChanged() }) { Icon(Icons.Default.BookmarkAdd, "Kelimeyi kaydet", tint = Gold) }
                }
            }
        }
        item { Text("Mini kontrol", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold) }
        item {
            Surface(shape = CardShape, color = Panel) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(q.question, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp)
                    q.options.forEachIndexed { index, option ->
                        val bg = when {
                            !checked -> Panel2
                            index == q.correctIndex -> Success.copy(alpha=.18f)
                            index == selected -> Danger.copy(alpha=.18f)
                            else -> Panel2
                        }
                        Surface(
                            shape = SmallShape,
                            color = bg,
                            modifier = Modifier.fillMaxWidth().clickable(enabled = !checked) { selected = index }
                        ) {
                            Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(selected = selected == index, onClick = null)
                                Spacer(Modifier.width(8.dp))
                                Text(option)
                            }
                        }
                    }
                    if (!checked) {
                        Button(onClick = {
                            if (selected >= 0) {
                                checked = true
                                val correct = if (selected == q.correctIndex) 1 else 0
                                store.completeLesson(lesson.id, q.topic, correct, 1)
                                store.markAction()
                                onChanged()
                            }
                        }, enabled = selected >= 0, modifier = Modifier.fillMaxWidth(), shape = SmallShape) { Text("Cevabı kontrol et") }
                    } else {
                        Text(if (selected == q.correctIndex) "✓ Doğru!" else "Doğru cevap: ${q.options[q.correctIndex]}", color = if(selected == q.correctIndex) Success else Gold, fontWeight = FontWeight.Bold)
                        Text(q.explanation, color = SoftText)
                        Text("+${if(selected == q.correctIndex) 58 else 50} XP", color = Mint, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
