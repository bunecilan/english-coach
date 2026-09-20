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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StoriesScreen(
    store: LearningStore,
    revision: Int,
    modifier: Modifier = Modifier,
    onLevelChanged: (CefrLevel) -> Unit,
    onStory: (Story) -> Unit
) {
    val level = store.selectedLevel
    val stories = StoryLibrary.forLevel(level)
    val done = store.completedStories()
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp,18.dp,18.dp,30.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("İngilizce Hikâyeler", fontSize = 30.sp, fontWeight = FontWeight.Black)
            Text("Oku • dokun ve Türkçesini gör • dinle • kelime kaydet", color = SoftText)
            Spacer(Modifier.height(14.dp))
            LevelStrip(level, onLevelChanged)
        }
        item {
            Surface(shape = CardShape, color = Color.Transparent) {
                Box(Modifier.background(Brush.linearGradient(listOf(levelColor(level).copy(alpha=.8f), Purple.copy(alpha=.75f)))).padding(18.dp)) {
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text("📚 Aynı hikâye, büyüyen İngilizce", fontSize=20.sp, fontWeight=FontWeight.Black)
                        Text("The Lost Wallet ve My New City hikâyeleri A1'den C2'ye aynı fikrin giderek daha doğal ve ileri anlatımını gösterir.", color = Color.White.copy(alpha=.9f))
                    }
                }
            }
        }
        items(stories) { story ->
            Surface(
                modifier = Modifier.fillMaxWidth().clickable { onStory(story) },
                shape = CardShape,
                color = Panel
            ) {
                Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    Surface(shape = SmallShape, color = levelColor(story.level).copy(alpha=.18f)) {
                        Text(story.emoji, Modifier.padding(14.dp), fontSize = 28.sp)
                    }
                    Spacer(Modifier.width(12.dp))
                    Column(Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(story.title, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
                            if (story.id in done) {
                                Spacer(Modifier.width(6.dp)); Icon(Icons.Default.CheckCircle, null, tint=Success, modifier=Modifier.size(18.dp))
                            }
                        }
                        Text("${story.level.label} • ${story.category}", color = levelColor(story.level), fontSize = 12.sp)
                        Text(story.description, color = SoftText, fontSize = 13.sp, maxLines=2)
                    }
                    Icon(Icons.Default.ChevronRight, null, tint = SoftText)
                }
            }
        }
    }
}

@Composable
fun StoryDetailScreen(
    story: Story,
    store: LearningStore,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onChanged: () -> Unit
) {
    val tts = rememberTtsController()
    var showAllTr by remember { mutableStateOf(false) }
    val revealed = remember { mutableStateMapOf<Int, Boolean>() }
    var selectedQuestion by remember { mutableIntStateOf(0) }
    var answer by remember { mutableIntStateOf(-1) }
    var checked by remember { mutableStateOf(false) }
    var fav by remember { mutableStateOf(story.id in store.favoriteStories()) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp,10.dp,18.dp,32.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack,"Geri") }
                Column(Modifier.weight(1f)) {
                    Text("${story.level.label} • ${story.category}", color = levelColor(story.level), fontWeight=FontWeight.Bold)
                    Text(story.title, fontSize=26.sp, fontWeight=FontWeight.Black)
                }
                IconButton(onClick = { fav = store.toggleFavoriteStory(story.id); onChanged() }) {
                    Icon(if(fav) Icons.Default.Favorite else Icons.Default.FavoriteBorder, "Favori", tint = if(fav) Pink else SoftText)
                }
            }
        }
        item {
            Surface(shape=CardShape, color=Panel) {
                Column(Modifier.padding(16.dp), verticalArrangement=Arrangement.spacedBy(8.dp)) {
                    Row(verticalAlignment=Alignment.CenterVertically) {
                        Text(story.emoji, fontSize=32.sp)
                        Spacer(Modifier.width(10.dp))
                        Column(Modifier.weight(1f)) {
                            Text("Hikâyeyi önce İngilizce anlamaya çalış", fontWeight=FontWeight.ExtraBold)
                            Text("Anlamadığın cümleye dokun. Türkçe sürekli gözünün önünde durmasın.", color=SoftText, fontSize=12.sp)
                        }
                    }
                    Row(horizontalArrangement=Arrangement.spacedBy(8.dp)) {
                        AssistChip(onClick={ showAllTr=!showAllTr }, label={Text(if(showAllTr) "Türkçeyi gizle" else "Tüm Türkçeyi göster")}, leadingIcon={Icon(Icons.Default.Translate,null)})
                        AssistChip(onClick={ tts.speak(story.sentences.joinToString(" ") { it.english }) }, label={Text("Tümünü dinle")}, leadingIcon={Icon(Icons.Default.Headphones,null)})
                    }
                }
            }
        }
        items(story.sentences.size) { index ->
            val sentence = story.sentences[index]
            val trVisible = showAllTr || revealed[index] == true
            Surface(
                shape=SmallShape,
                color=if(trVisible) Panel2 else Panel,
                modifier=Modifier.fillMaxWidth().clickable { revealed[index] = !(revealed[index] ?: false) }
            ) {
                Column(Modifier.padding(15.dp), verticalArrangement=Arrangement.spacedBy(7.dp)) {
                    Row(verticalAlignment=Alignment.CenterVertically) {
                        Text("${index+1}", color=levelColor(story.level), fontWeight=FontWeight.Black, modifier=Modifier.width(26.dp))
                        Text(sentence.english, fontSize=17.sp, fontWeight=FontWeight.SemiBold, modifier=Modifier.weight(1f))
                        IconButton(onClick={ tts.speak(sentence.english) }) { Icon(Icons.Default.VolumeUp,"Dinle",tint=Mint) }
                        IconButton(onClick={ tts.speak(sentence.english, true) }) { Icon(Icons.Default.SlowMotionVideo,"Yavaş",tint=Sky) }
                    }
                    if (trVisible) {
                        Text(sentence.turkish, color=SoftText, modifier=Modifier.padding(start=26.dp))
                    } else {
                        Text("Türkçesi için dokun", color=levelColor(story.level), fontSize=11.sp, modifier=Modifier.padding(start=26.dp))
                    }
                }
            }
        }
        item { Text("Bu hikâyeden kelimeler", fontSize=20.sp, fontWeight=FontWeight.ExtraBold) }
        items(story.vocabulary.size) { i ->
            val v = story.vocabulary[i]
            Surface(shape=SmallShape,color=Panel) {
                Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment=Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text(v.word, fontWeight=FontWeight.ExtraBold)
                        Text(v.meaning,color=levelColor(story.level))
                    }
                    IconButton(onClick={tts.speak(v.word)}) { Icon(Icons.Default.VolumeUp,null) }
                    IconButton(onClick={store.saveWord(v.word,v.meaning);onChanged()}) { Icon(Icons.Default.BookmarkAdd,null,tint=Gold) }
                }
            }
        }
        item { Text("Ne anladın?", fontSize=20.sp, fontWeight=FontWeight.ExtraBold) }
        if (story.questions.isNotEmpty()) {
            item {
                val q = story.questions[selectedQuestion.coerceIn(0, story.questions.lastIndex)]
                Surface(shape=CardShape,color=Panel) {
                    Column(Modifier.padding(18.dp), verticalArrangement=Arrangement.spacedBy(9.dp)) {
                        Text("${selectedQuestion+1}/${story.questions.size}", color=levelColor(story.level), fontWeight=FontWeight.Bold)
                        Text(q.question,fontWeight=FontWeight.ExtraBold,fontSize=17.sp)
                        q.options.forEachIndexed { index,opt ->
                            val bg = when {
                                !checked -> Panel2
                                index==q.correctIndex -> Success.copy(alpha=.18f)
                                index==answer -> Danger.copy(alpha=.18f)
                                else -> Panel2
                            }
                            Surface(shape=SmallShape,color=bg,modifier=Modifier.fillMaxWidth().clickable(enabled=!checked){answer=index}) {
                                Row(Modifier.padding(12.dp),verticalAlignment=Alignment.CenterVertically) {
                                    RadioButton(selected=answer==index,onClick=null);Spacer(Modifier.width(6.dp));Text(opt)
                                }
                            }
                        }
                        if (!checked) {
                            Button(onClick={if(answer>=0){checked=true;store.recordTopic(q.topic,if(answer==q.correctIndex)1 else 0,1);onChanged()}}, enabled=answer>=0, modifier=Modifier.fillMaxWidth(),shape=SmallShape){Text("Kontrol et")}
                        } else {
                            Text(if(answer==q.correctIndex) "✓ Doğru" else "Doğru cevap: ${q.options[q.correctIndex]}",color=if(answer==q.correctIndex) Success else Gold,fontWeight=FontWeight.Bold)
                            Text(q.explanation,color=SoftText)
                            if(selectedQuestion < story.questions.lastIndex) {
                                OutlinedButton(onClick={selectedQuestion++;answer=-1;checked=false},modifier=Modifier.fillMaxWidth(),shape=SmallShape){Text("Sonraki soru")}
                            } else {
                                Button(onClick={
                                    store.completeStory(story.id, if(answer==q.correctIndex) 1 else 0, story.questions.size)
                                    store.markAction(); onChanged(); onBack()
                                },modifier=Modifier.fillMaxWidth(),shape=SmallShape){Text("Hikâyeyi tamamla")}
                            }
                        }
                    }
                }
            }
        }
        item {
            Surface(shape=CardShape,color=levelColor(story.level).copy(alpha=.12f)) {
                Column(Modifier.padding(18.dp),verticalArrangement=Arrangement.spacedBy(6.dp)) {
                    Text("🗣️ Hikâyeyi sen anlat",fontWeight=FontWeight.ExtraBold,fontSize=18.sp)
                    Text("Metni kapatıp 2–3 İngilizce cümleyle ne olduğunu anlatmayı dene. Mükemmel olmak zorunda değil; amaç hatırlayıp üretmek.",color=SoftText)
                }
            }
        }
    }
}
