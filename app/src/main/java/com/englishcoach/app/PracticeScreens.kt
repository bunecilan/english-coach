package com.englishcoach.app

import android.Manifest
import android.app.Activity
import android.speech.RecognizerIntent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import kotlinx.coroutines.delay

@Composable
fun PracticeHubScreen(
    store: LearningStore,
    revision: Int,
    modifier: Modifier = Modifier,
    onSpeaking: () -> Unit,
    onVocabulary: () -> Unit,
    onYds: () -> Unit
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp,18.dp,18.dp,30.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("Pratik Merkezi", fontSize=30.sp, fontWeight=FontWeight.Black)
            Text("Bilgiyi konuşmaya ve gerçek kullanıma çevir.",color=SoftText)
        }
        item {
            PracticeCard("🎤","Konuşma Laboratuvarı","Mikrofonla cümle söyle, telefonun ne anladığını gör ve hedef cümleyle karşılaştır.",Mint,onSpeaking)
        }
        item {
            PracticeCard("🧠","Kelime Defterim","Hikâye ve derslerden kaydettiğin kelimeleri tekrar et.",Gold,onVocabulary)
        }
        item {
            PracticeCard("🎯","YDS / Sınav Merkezi","Normal öğrenme yolundan ayrı: YDS taktikleri, sık yapı aileleri ve süreli mini deneme.",Pink,onYds)
        }
        item {
            Surface(shape=CardShape,color=Panel) {
                Column(Modifier.padding(18.dp),verticalArrangement=Arrangement.spacedBy(8.dp)) {
                    Text("⚡ Adaptif zorluk",fontSize=19.sp,fontWeight=FontWeight.ExtraBold)
                    Text("Uygulama konu bazındaki doğru/yanlış geçmişine göre kolay → orta → zor önerir. Sürekli zorlandığın alanlar Akıllı Tekrar'a taşınır.",color=SoftText)
                    val topics = Curriculum.forLevel(store.selectedLevel).mapNotNull { it.practice.firstOrNull()?.topic }.distinct()
                    topics.take(4).forEach { topic ->
                        Row(verticalAlignment=Alignment.CenterVertically) {
                            Text(topic,Modifier.weight(1f))
                            Text("${store.topicMastery(topic)}% • ${store.suggestedDifficulty(topic)}",color=levelColor(store.selectedLevel),fontSize=12.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun PracticeCard(emoji:String,title:String,desc:String,tint:Color,onClick:()->Unit) {
    Surface(shape=CardShape,color=Panel,modifier=Modifier.fillMaxWidth().clickable(onClick=onClick)) {
        Row(Modifier.padding(18.dp),verticalAlignment=Alignment.CenterVertically) {
            Surface(shape=SmallShape,color=tint.copy(alpha=.15f)) { Text(emoji,Modifier.padding(14.dp),fontSize=30.sp) }
            Spacer(Modifier.width(14.dp))
            Column(Modifier.weight(1f)) {
                Text(title,fontSize=19.sp,fontWeight=FontWeight.ExtraBold)
                Text(desc,color=SoftText,fontSize=13.sp)
            }
            Icon(Icons.Default.ChevronRight,null,tint=SoftText)
        }
    }
}

@Composable
fun SpeakingPracticeScreen(
    store: LearningStore,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onChanged: () -> Unit
) {
    val tts = rememberTtsController()
    val scenarios = SpeakingContent.forLevel(store.selectedLevel)
    var scenarioIndex by remember { mutableIntStateOf(0) }
    val scenario = scenarios.getOrElse(scenarioIndex.coerceAtMost(scenarios.lastIndex.coerceAtLeast(0))) { SpeakingContent.scenarios.first() }
    var promptIndex by remember { mutableIntStateOf(0) }
    val model = scenario.modelAnswers.getOrElse(promptIndex) { scenario.modelAnswers.first() }
    var heard by remember { mutableStateOf("") }
    var score by remember { mutableIntStateOf(-1) }

    val speechLauncher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val matches = result.data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            heard = matches?.firstOrNull().orEmpty()
            score = pronunciationScore(model, heard)
            store.markAction()
            store.xp += when { score >= 90 -> 20; score >= 70 -> 12; else -> 6 }
            onChanged()
        }
    }
    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) speechLauncher.launch(speechIntent("Say it in English"))
    }

    LazyColumn(
        modifier=modifier.fillMaxSize(),
        contentPadding=PaddingValues(18.dp,10.dp,18.dp,30.dp),
        verticalArrangement=Arrangement.spacedBy(14.dp)
    ) {
        item {
            Row(verticalAlignment=Alignment.CenterVertically) {
                IconButton(onClick=onBack){Icon(Icons.Default.ArrowBack,"Geri")}
                Column {
                    Text("Konuşma Laboratuvarı",fontSize=27.sp,fontWeight=FontWeight.Black)
                    Text("${store.selectedLevel.label} • gerçek hayat diyaloğu",color=levelColor(store.selectedLevel))
                }
            }
        }
        item {
            LevelStrip(store.selectedLevel) { store.selectedLevel=it; scenarioIndex=0;promptIndex=0;heard="";score=-1;onChanged() }
        }
        item {
            Surface(shape=CardShape,color=Panel) {
                Column(Modifier.padding(18.dp),verticalArrangement=Arrangement.spacedBy(9.dp)) {
                    Text("🎭 ${scenario.title}",fontSize=21.sp,fontWeight=FontWeight.Black)
                    Text(scenario.context,color=SoftText)
                    if(scenarios.size>1) {
                        TextButton(onClick={scenarioIndex=(scenarioIndex+1)%scenarios.size;promptIndex=0;heard="";score=-1}) { Text("Başka senaryo →") }
                    }
                }
            }
        }
        item {
            Surface(shape=CardShape,color=Purple.copy(alpha=.15f)) {
                Column(Modifier.padding(18.dp),verticalArrangement=Arrangement.spacedBy(10.dp)) {
                    Text("Uygulama soruyor",color=Purple,fontWeight=FontWeight.Bold)
                    Text(scenario.prompts.getOrElse(promptIndex){scenario.prompts.first()},fontSize=22.sp,fontWeight=FontWeight.Black)
                    Row {
                        Button(onClick={tts.speak(scenario.prompts.getOrElse(promptIndex){scenario.prompts.first()})},shape=SmallShape){Icon(Icons.Default.VolumeUp,null);Spacer(Modifier.width(6.dp));Text("Soruyu dinle")}
                    }
                }
            }
        }
        item {
            Surface(shape=CardShape,color=Panel) {
                Column(Modifier.padding(18.dp),horizontalAlignment=Alignment.CenterHorizontally,verticalArrangement=Arrangement.spacedBy(12.dp)) {
                    Text("Örnek cevap",color=SoftText)
                    Text(model,fontSize=20.sp,fontWeight=FontWeight.ExtraBold)
                    Row(horizontalArrangement=Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(onClick={tts.speak(model)},shape=SmallShape){Icon(Icons.Default.VolumeUp,null);Spacer(Modifier.width(5.dp));Text("Dinle")}
                        OutlinedButton(onClick={tts.speak(model,true)},shape=SmallShape){Text("🐢 Yavaş")}
                    }
                    Button(onClick={permissionLauncher.launch(Manifest.permission.RECORD_AUDIO)},modifier=Modifier.fillMaxWidth().height(54.dp),shape=SmallShape) {
                        Icon(Icons.Default.Mic,null);Spacer(Modifier.width(8.dp));Text("Ben de söyleyeyim")
                    }
                    if(heard.isNotBlank()) {
                        HorizontalDivider(color=Panel2)
                        Text("Telefonun duyduğu:",color=SoftText,fontSize=12.sp)
                        Text(heard,fontWeight=FontWeight.Bold)
                        Text("Yakınlık: $score%",fontSize=25.sp,fontWeight=FontWeight.Black,color=when{score>=85->Success;score>=65->Gold;else->Pink})
                        Text(when{score>=90->"Çok yakın! Akıcılık ve ton üzerinde çalışabilirsin.";score>=70->"Gayet iyi. Bir kez daha, biraz daha yavaş söyle.";else->"Sorun değil. Önce yavaş dinle, cümleyi iki parçaya böl ve tekrar dene."},color=SoftText)
                    }
                }
            }
        }
        item {
            Button(onClick={
                promptIndex=(promptIndex+1)%scenario.prompts.size
                heard="";score=-1
            },modifier=Modifier.fillMaxWidth(),shape=SmallShape){Text("Sonraki konuşma adımı →")}
        }
        item {
            Surface(shape=CardShape,color=Panel2) {
                Column(Modifier.padding(16.dp)) {
                    Text("Not",fontWeight=FontWeight.Bold)
                    Text("Puan, telefonun konuşma tanıma sonucuyla hedef cümlenin metinsel yakınlığıdır; profesyonel bir aksan/telaffuz ölçümü değildir. Ama pratikte 'telefon beni anlıyor mu?' kontrolü için faydalıdır.",color=SoftText,fontSize=12.sp)
                }
            }
        }
    }
}

@Composable
fun VocabularyNotebookScreen(
    store: LearningStore,
    revision: Int,
    modifier: Modifier=Modifier,
    onBack:()->Unit,
    onChanged:()->Unit
) {
    val tts=rememberTtsController()
    val words=store.savedWords().map { it.substringBefore("|||") to it.substringAfter("|||","") }.sortedBy { it.first.lowercase() }
    LazyColumn(modifier.fillMaxSize(),contentPadding=PaddingValues(18.dp,10.dp,18.dp,30.dp),verticalArrangement=Arrangement.spacedBy(10.dp)) {
        item {
            Row(verticalAlignment=Alignment.CenterVertically){
                IconButton(onClick=onBack){Icon(Icons.Default.ArrowBack,"Geri")}
                Column { Text("Kelime Defterim",fontSize=27.sp,fontWeight=FontWeight.Black);Text("${words.size} kayıtlı kelime",color=SoftText) }
            }
        }
        if(words.isEmpty()) item {
            Surface(shape=CardShape,color=Panel){Column(Modifier.padding(22.dp),horizontalAlignment=Alignment.CenterHorizontally){Text("🔖",fontSize=42.sp);Text("Henüz kelime kaydetmedin",fontWeight=FontWeight.Bold);Text("Ders veya hikâyelerdeki sarı yer imi düğmesine bas.",color=SoftText)}}
        }
        items(words){(word,meaning)->
            Surface(shape=SmallShape,color=Panel){Row(Modifier.fillMaxWidth().padding(14.dp),verticalAlignment=Alignment.CenterVertically){
                Column(Modifier.weight(1f)){Text(word,fontWeight=FontWeight.ExtraBold,fontSize=18.sp);Text(meaning,color=Mint)}
                IconButton(onClick={tts.speak(word)}){Icon(Icons.Default.VolumeUp,null)}
                IconButton(onClick={store.removeWord(word);onChanged()}){Icon(Icons.Default.DeleteOutline,null,tint=Danger)}
            }}
        }
    }
}

@Composable
fun YdsHubScreen(modifier:Modifier=Modifier,onBack:()->Unit,onQuiz:()->Unit) {
    LazyColumn(modifier.fillMaxSize(),contentPadding=PaddingValues(18.dp,10.dp,18.dp,30.dp),verticalArrangement=Arrangement.spacedBy(12.dp)) {
        item { Row(verticalAlignment=Alignment.CenterVertically){IconButton(onClick=onBack){Icon(Icons.Default.ArrowBack,"Geri")};Column{Text("YDS / Sınav Merkezi",fontSize=27.sp,fontWeight=FontWeight.Black);Text("Ana İngilizce yolundan ayrı çalışma modu",color=Pink)}} }
        item { Surface(shape=CardShape,color=Pink.copy(alpha=.12f)){Column(Modifier.padding(18.dp),verticalArrangement=Arrangement.spacedBy(7.dp)){Text("🎯 Buradaki amaç",fontWeight=FontWeight.ExtraBold,fontSize=19.sp);Text("Normal İngilizce öğrenimini sınava çevirmiyoruz. Bu bölüm yalnızca YDS seçen kullanıcı için: yapı aileleri, bağlaçlar, kelime kalıpları, paragraf taktikleri ve süreli soru çözümü.",color=SoftText);Button(onClick=onQuiz,shape=SmallShape){Icon(Icons.Default.Timer,null);Spacer(Modifier.width(6.dp));Text("10 soruluk mini deneme")}}} }
        items(YdsContent.tactics){tactic->
            Surface(shape=CardShape,color=Panel){Column(Modifier.padding(18.dp),verticalArrangement=Arrangement.spacedBy(7.dp)){Text("${tactic.icon} ${tactic.title}",fontSize=19.sp,fontWeight=FontWeight.ExtraBold);Text(tactic.summary,color=SoftText);tactic.details.forEach{Text("• $it",fontSize=13.sp)};Text("Sık yapı aileleri",color=Pink,fontWeight=FontWeight.Bold);Text(tactic.patterns.joinToString("  •  "),color=SoftText,fontSize=12.sp)}}
        }
        item { Surface(shape=CardShape,color=Panel2){Text("Not: Buradaki sorular ve örnekler özgün hazırlanmıştır; geçmiş sınav soruları birebir kopyalanmaz. YDS'de tekrar eden yapı ve soru mantıkları öğretilir.",Modifier.padding(16.dp),color=SoftText,fontSize=12.sp)} }
    }
}

@Composable
fun YdsQuizScreen(
    store: LearningStore,
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    onChanged: () -> Unit
) {
    val questions = YdsContent.questions
    var index by remember { mutableIntStateOf(0) }
    var selected by remember { mutableIntStateOf(-1) }
    var correct by remember { mutableIntStateOf(0) }
    var finished by remember { mutableStateOf(false) }
    var seconds by remember { mutableIntStateOf(12 * 60) }
    val answers = remember { mutableStateListOf<Pair<Int, Int>>() }

    LaunchedEffect(finished) {
        while (!finished && seconds > 0) {
            delay(1000)
            seconds--
        }
        if (seconds <= 0) finished = true
    }

    if (finished) {
        LazyColumn(
            modifier.fillMaxSize(),
            contentPadding = PaddingValues(18.dp, 12.dp, 18.dp, 30.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = onBack) { Icon(Icons.Default.ArrowBack, "Geri") }
                    Text("Mini Deneme Sonucu", fontSize = 27.sp, fontWeight = FontWeight.Black)
                }
            }
            item {
                Surface(shape = CardShape, color = Panel) {
                    Column(Modifier.padding(22.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("🎯", fontSize = 42.sp)
                        Text("$correct / ${questions.size}", fontSize = 34.sp, fontWeight = FontWeight.Black, color = Pink)
                        Text("Kalan süre: %02d:%02d".format(seconds / 60, seconds % 60), color = SoftText)
                    }
                }
            }
            items(answers.size) { i ->
                val (qi, ai) = answers[i]
                val item = questions[qi]
                Surface(shape = SmallShape, color = Panel) {
                    Column(Modifier.padding(14.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text("${qi + 1}. ${item.question}", fontWeight = FontWeight.Bold)
                        if (ai == item.correctIndex) {
                            Text("✓ Doğru", color = Success)
                        } else {
                            val chosen = item.options.getOrElse(ai) { "Boş" }
                            Text("Sen: $chosen", color = Danger)
                            Text("Doğru: ${item.options[item.correctIndex]}", color = Gold)
                        }
                        Text(item.explanation, color = SoftText, fontSize = 12.sp)
                    }
                }
            }
        }
        return
    }

    val current = questions[index]
    LazyColumn(
        modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp, 12.dp, 18.dp, 30.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = onBack) { Icon(Icons.Default.Close, "Çık") }
                Column(Modifier.weight(1f)) {
                    Text("YDS Mini Deneme", fontWeight = FontWeight.Black, fontSize = 23.sp)
                    Text("${index + 1}/${questions.size}", color = SoftText)
                }
                Text("⏱ %02d:%02d".format(seconds / 60, seconds % 60), color = Gold, fontWeight = FontWeight.Bold)
            }
        }
        item {
            LinearProgressIndicator(progress = { index.toFloat() / questions.size }, modifier = Modifier.fillMaxWidth())
        }
        item {
            Surface(shape = CardShape, color = Panel) {
                Column(Modifier.padding(18.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(current.question, fontSize = 19.sp, fontWeight = FontWeight.ExtraBold)
                    current.options.forEachIndexed { i, option ->
                        Surface(
                            shape = SmallShape,
                            color = if (selected == i) Purple.copy(alpha = .2f) else Panel2,
                            modifier = Modifier.fillMaxWidth().clickable { selected = i }
                        ) {
                            Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                                RadioButton(selected = selected == i, onClick = null)
                                Spacer(Modifier.width(8.dp))
                                Text(option)
                            }
                        }
                    }
                }
            }
        }
        item {
            Button(
                onClick = {
                    if (selected >= 0) {
                        answers.add(index to selected)
                        val isCorrect = selected == current.correctIndex
                        if (isCorrect) correct++
                        store.recordTopic(current.topic, if (isCorrect) 1 else 0, 1)
                        if (index == questions.lastIndex) {
                            finished = true
                            store.xp += (correct + if (isCorrect) 1 else 0) * 6
                            store.markAction()
                            onChanged()
                        } else {
                            index++
                            selected = -1
                        }
                    }
                },
                enabled = selected >= 0,
                modifier = Modifier.fillMaxWidth(),
                shape = SmallShape
            ) {
                Text(if (index == questions.lastIndex) "Sınavı bitir" else "Sonraki soru")
            }
        }
    }
}

@Composable
fun ProfileScreen(store: LearningStore, revision: Int, modifier: Modifier = Modifier) {
    val completed = store.completedLessons().size
    val stories = store.completedStories().size
    val words = store.savedWords().size
    val badges = listOf(
        Triple("🌱", "İlk Adım", completed >= 1),
        Triple("🔥", "Seri Başladı", store.streak >= 3),
        Triple("📚", "Okur", stories >= 3),
        Triple("🧠", "Kelime Avcısı", words >= 20),
        Triple("⭐", "500 XP", store.xp >= 500),
        Triple("🏆", "1000 XP", store.xp >= 1000)
    )

    LazyColumn(
        modifier.fillMaxSize(),
        contentPadding = PaddingValues(18.dp, 18.dp, 18.dp, 30.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text("İlerlemem", fontSize = 30.sp, fontWeight = FontWeight.Black)
            Text("Baskı değil, görünür ilerleme.", color = SoftText)
        }
        item {
            Surface(shape = CardShape, color = Panel) {
                Column(Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row {
                        Column(Modifier.weight(1f)) {
                            Text("Seviye", color = SoftText)
                            Text(
                                "${store.selectedLevel.label} ${store.selectedLevel.tr}",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Black,
                                color = levelColor(store.selectedLevel)
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text("Toplam XP", color = SoftText)
                            Text("${store.xp}", fontSize = 22.sp, fontWeight = FontWeight.Black)
                        }
                    }
                    HorizontalDivider(color = Panel2)
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Stat("🔥", "${store.streak}", "seri")
                        Stat("✓", "$completed", "ders")
                        Stat("📖", "$stories", "hikâye")
                        Stat("🔖", "$words", "kelime")
                    }
                }
            }
        }
        item { Text("Rozetler", fontSize = 20.sp, fontWeight = FontWeight.ExtraBold) }
        items(badges) { (emoji, title, earned) ->
            Surface(shape = SmallShape, color = if (earned) Gold.copy(alpha = .13f) else Panel) {
                Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(emoji, fontSize = 26.sp)
                    Spacer(Modifier.width(12.dp))
                    Text(title, Modifier.weight(1f), fontWeight = FontWeight.Bold, color = if (earned) Color.White else SoftText)
                    Text(if (earned) "Kazanıldı" else "Kilitli", color = if (earned) Gold else SoftText, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
private fun Stat(emoji: String, value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(emoji)
        Text(value, fontWeight = FontWeight.Black, fontSize = 18.sp)
        Text(label, color = SoftText, fontSize = 11.sp)
    }
}
