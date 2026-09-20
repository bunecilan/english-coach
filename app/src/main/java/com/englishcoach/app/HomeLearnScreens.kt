package com.englishcoach.app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen(
    store: LearningStore,
    revision: Int,
    modifier: Modifier = Modifier,
    onLearn: () -> Unit,
    onStories: () -> Unit,
    onSpeaking: () -> Unit,
    onYds: () -> Unit,
    onLesson: (Lesson) -> Unit
) {
    val level = store.selectedLevel
    val lessons = Curriculum.forLevel(level)
    val completed = store.completedLessons()
    val next = lessons.firstOrNull { it.id !in completed } ?: lessons.firstOrNull()
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp, 18.dp, 18.dp, 28.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Column(Modifier.weight(1f)) {
                    Text("İngilizce Koçu", fontSize = 30.sp, fontWeight = FontWeight.Black)
                    Text("Gerçek İngilizce • A1 → C2", color = SoftText, fontSize = 14.sp)
                }
                Surface(shape = CircleShape, color = Panel2) {
                    Text("🔥 ${store.streak}", Modifier.padding(horizontal = 14.dp, vertical = 9.dp), fontWeight = FontWeight.Bold)
                }
            }
        }
        item {
            Card(
                shape = CardShape,
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    Modifier
                        .background(Brush.linearGradient(listOf(Color(0xFF5C3CF0), Color(0xFFB84AE3), Color(0xFFFF648D))))
                        .padding(20.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(shape = SmallShape, color = Color.White.copy(alpha = .18f)) {
                                Text("${level.label} • ${level.tr}", Modifier.padding(horizontal = 12.dp, vertical = 7.dp), fontWeight = FontWeight.Bold)
                            }
                            Spacer(Modifier.weight(1f))
                            Text("${store.xp} XP", fontWeight = FontWeight.ExtraBold)
                        }
                        Text("Bugün sadece 15 dakika", fontSize = 24.sp, fontWeight = FontWeight.Black)
                        Text("1 mini ders • 1 konuşma • 1 kısa hikâye", color = Color.White.copy(alpha = .85f))
                        LinearProgressIndicator(
                            progress = { (completed.count { id -> lessons.any { it.id == id } }.toFloat() / lessons.size.coerceAtLeast(1)).coerceIn(0f,1f) },
                            modifier = Modifier.fillMaxWidth().height(8.dp).clip(CircleShape),
                            color = Color.White,
                            trackColor = Color.White.copy(alpha = .22f)
                        )
                        if (next != null) {
                            Button(
                                onClick = { onLesson(next) },
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF402E9B)),
                                shape = SmallShape
                            ) {
                                Icon(Icons.Default.PlayArrow, null)
                                Spacer(Modifier.width(6.dp))
                                Text("Derse devam et: ${next.title}", maxLines = 1, overflow = TextOverflow.Ellipsis)
                            }
                        }
                    }
                }
            }
        }
        item {
            Text("Ne çalışmak istiyorsun?", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
        }
        item {
            val scroll = rememberScrollState()
            Row(Modifier.horizontalScroll(scroll), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                FeaturePill("📚", "A1–C2 Yol", "Seviye seviye", onLearn)
                FeaturePill("📖", "Hikâyeler", "Türkçe aç/kapat", onStories)
                FeaturePill("🎤", "Konuşma", "Mikrofonla tekrar", onSpeaking)
                FeaturePill("🎯", "YDS Merkezi", "Taktik + soru", onYds)
            }
        }
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Seviyen", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
                Spacer(Modifier.weight(1f))
                Text("Değiştir →", color = Mint, modifier = Modifier.clickable { onLearn() })
            }
        }
        item {
            LevelStrip(selected = level, onSelect = {
                store.selectedLevel = it
                // revision is read by caller; UI still recomposes via click hierarchy when navigating.
                onLearn()
            })
        }
        item {
            Surface(shape = CardShape, color = Panel) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text("🧠 Akıllı tekrar", fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                    val weak = lessons.minByOrNull { store.topicMastery(it.practice.firstOrNull()?.topic ?: it.title) }
                    if (weak != null) {
                        val topic = weak.practice.firstOrNull()?.topic ?: weak.title
                        Text("Şu anda en çok güçlendirebileceğin alan:", color = SoftText)
                        Text(weak.title, fontWeight = FontWeight.Bold, fontSize = 17.sp)
                        Text("Ustalık: ${store.topicMastery(topic)}% • Önerilen: ${store.suggestedDifficulty(topic)}", color = levelColor(level))
                        OutlinedButton(onClick = { onLesson(weak) }, shape = SmallShape) { Text("Tekrar et") }
                    }
                }
            }
        }
        item {
            Text("Bugünün mini planı", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold)
        }
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                DailyRow("1", "Kısa ders", next?.title ?: "Seviyeni seç", completed.isNotEmpty())
                DailyRow("2", "Konuşma", "Bir rol yapma diyaloğu", false)
                DailyRow("3", "Hikâye", "Seviyene uygun 5–8 cümle", false)
            }
        }
    }
}

@Composable
private fun FeaturePill(emoji: String, title: String, subtitle: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.width(180.dp).clickable(onClick = onClick),
        shape = CardShape,
        color = Panel
    ) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text(emoji, fontSize = 28.sp)
            Text(title, fontWeight = FontWeight.ExtraBold)
            Text(subtitle, color = SoftText, fontSize = 12.sp)
        }
    }
}

@Composable
private fun DailyRow(number: String, title: String, subtitle: String, done: Boolean) {
    Surface(shape = SmallShape, color = Panel) {
        Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Surface(shape = CircleShape, color = if (done) Success.copy(alpha=.2f) else Purple.copy(alpha=.2f)) {
                Text(if (done) "✓" else number, Modifier.padding(horizontal=11.dp, vertical=7.dp), color = if(done) Success else Purple, fontWeight=FontWeight.Black)
            }
            Spacer(Modifier.width(12.dp))
            Column {
                Text(title, fontWeight = FontWeight.Bold)
                Text(subtitle, color = SoftText, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun LevelStrip(selected: CefrLevel, onSelect: (CefrLevel) -> Unit) {
    Row(Modifier.horizontalScroll(rememberScrollState()), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        CefrLevel.entries.forEach { level ->
            FilterChip(
                selected = selected == level,
                onClick = { onSelect(level) },
                label = { Text("${level.label} ${level.tr}") },
                colors = FilterChipDefaults.filterChipColors(
                    selectedContainerColor = levelColor(level).copy(alpha=.22f),
                    selectedLabelColor = levelColor(level),
                    containerColor = Panel
                )
            )
        }
    }
}

@Composable
fun LearnScreen(
    store: LearningStore,
    revision: Int,
    modifier: Modifier = Modifier,
    onLevelChanged: (CefrLevel) -> Unit,
    onLesson: (Lesson) -> Unit
) {
    val level = store.selectedLevel
    val lessons = Curriculum.forLevel(level)
    val completed = store.completedLessons()
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp,18.dp,18.dp,28.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Öğrenme Yolu", fontSize = 30.sp, fontWeight = FontWeight.Black)
            Text("Temelden ileriye, konuşma odaklı ama dört beceri birlikte.", color = SoftText)
            Spacer(Modifier.height(14.dp))
            LevelStrip(level, onLevelChanged)
        }
        item {
            Surface(shape = CardShape, color = levelColor(level).copy(alpha=.14f)) {
                Column(Modifier.padding(18.dp)) {
                    Text("${level.label} • ${level.tr}", fontSize = 24.sp, fontWeight = FontWeight.Black, color = levelColor(level))
                    Text(level.accent, color = SoftText)
                    Spacer(Modifier.height(10.dp))
                    val done = lessons.count { it.id in completed }
                    LinearProgressIndicator(progress = { done.toFloat()/lessons.size.coerceAtLeast(1) }, modifier=Modifier.fillMaxWidth().height(7.dp).clip(CircleShape))
                    Spacer(Modifier.height(6.dp))
                    Text("$done / ${lessons.size} ders tamamlandı", color = SoftText, fontSize = 12.sp)
                }
            }
        }
        items(lessons) { lesson ->
            Surface(
                shape = CardShape,
                color = Panel,
                modifier = Modifier.fillMaxWidth().clickable { onLesson(lesson) }
            ) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = SmallShape, color = levelColor(level).copy(alpha=.18f)) {
                        Text(lesson.skill.emoji, Modifier.padding(13.dp), fontSize = 24.sp)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("${lesson.order}.", color = levelColor(level), fontWeight = FontWeight.Black)
                            Spacer(Modifier.width(6.dp))
                            Text(lesson.title, fontWeight = FontWeight.ExtraBold, fontSize = 17.sp)
                        }
                        Text(lesson.subtitle, color = SoftText, fontSize = 13.sp)
                        Text("${lesson.skill.emoji} ${lesson.skill.title}", color = levelColor(level), fontSize = 12.sp)
                    }
                    Icon(if (lesson.id in completed) Icons.Default.CheckCircle else Icons.Default.ChevronRight, null, tint = if(lesson.id in completed) Success else SoftText)
                }
            }
        }
        item {
            Surface(shape = CardShape, color = Panel2) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text("Bu seviye bitince ne olacak?", fontWeight = FontWeight.ExtraBold)
                    Text(when(level) {
                        CefrLevel.A1 -> "Kendini tanıtır, temel ihtiyaçlarını söyler, basit günlük konuşmaları başlatırsın."
                        CefrLevel.A2 -> "Seyahat, alışveriş ve günlük yaşamda daha bağımsız konuşursun."
                        CefrLevel.B1 -> "Görüş belirtir, geçmiş deneyimleri anlatır ve sohbeti sürdürebilirsin."
                        CefrLevel.B2 -> "Daha doğal, detaylı ve rahat iletişim kurar; tartışmalara katılırsın."
                        CefrLevel.C1 -> "İleri ve profesyonel konularda nüanslı, esnek dil kullanırsın."
                        CefrLevel.C2 -> "Ton, ima ve stil dahil çok ileri düzeyde esnek iletişim kurarsın."
                    }, color = SoftText)
                }
            }
        }
    }
}
