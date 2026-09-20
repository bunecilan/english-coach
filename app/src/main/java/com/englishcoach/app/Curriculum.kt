package com.englishcoach.app

object Curriculum {
    val lessons: List<Lesson> = listOf(
        Lesson(
            id="a1-hello", level=CefrLevel.A1, order=1, title="Hello! İlk cümlelerin", subtitle="Selamlaşma, tanışma, kendini tanıtma", skill=SkillType.SPEAKING,
            explanation="İngilizcede ilk hedef uzun cümle kurmak değil, kendini basit ve doğru tanıtmaktır. I am... ve My name is... kalıpları bunun temelidir.", simpleExplanation="I = ben. am = -im/-ım gibi düşün. I am Ali. = Ben Ali'yim.",
            examples=listOf(ExampleLine("Hello, I am Ece.", "Merhaba, ben Ece’yim."), ExampleLine("My name is Can.", "Benim adım Can."), ExampleLine("Nice to meet you.", "Tanıştığımıza memnun oldum.")),
            vocabulary=listOf(VocabItem("hello", "merhaba", "Hello, I am Ece.", "Merhaba, ben Ece’yim."), VocabItem("name", "isim", "My name is Can.", "Benim adım Can."), VocabItem("meet", "tanışmak", "Nice to meet you.", "Tanıştığımıza memnun oldum.")),
            practice=listOf(PracticeQuestion("Ben Mert’im. hangisi?", listOf("I Mert", "I am Mert", "Me is Mert", "My Mert"), 1, "I ile am kullanılır.", "to be"))
        ),
        Lesson(
            id="a1-be", level=CefrLevel.A1, order=2, title="am / is / are", subtitle="İngilizcenin en temel fiili", skill=SkillType.GRAMMAR,
            explanation="To be Türkçedeki olmak ve isim cümlelerindeki eklerin işini yapar. I am, he/she/it is, you/we/they are.", simpleExplanation="I → am, tek kişi/şey → is, you/we/they → are.",
            examples=listOf(ExampleLine("I am tired.", "Yorgunum."), ExampleLine("She is a doctor.", "O bir doktor."), ExampleLine("They are at home.", "Onlar evde.")),
            vocabulary=listOf(VocabItem("tired", "yorgun", "I am tired.", "Yorgunum."), VocabItem("doctor", "doktor", "She is a doctor.", "O bir doktor."), VocabItem("home", "ev", "They are at home.", "Onlar evde.")),
            practice=listOf(PracticeQuestion("She ___ happy.", listOf("am", "is", "are", "be"), 1, "She ile is gelir.", "to be"))
        ),
        Lesson(
            id="a1-present", level=CefrLevel.A1, order=3, title="Günlük rutin", subtitle="Present Simple ile her günü anlat", skill=SkillType.GRAMMAR,
            explanation="Present Simple alışkanlıklar, rutinler ve genel doğrular için kullanılır. He/she/it ile fiil çoğunlukla -s alır.", simpleExplanation="Her gün yapıyorsan Present Simple düşün: I work. She works.",
            examples=listOf(ExampleLine("I drink coffee every morning.", "Her sabah kahve içerim."), ExampleLine("He works in a bank.", "Bankada çalışır."), ExampleLine("We live in Istanbul.", "İstanbul’da yaşarız.")),
            vocabulary=listOf(VocabItem("work", "çalışmak", "He works in a bank.", "Bankada çalışır."), VocabItem("live", "yaşamak", "We live in Istanbul.", "İstanbul’da yaşarız."), VocabItem("morning", "sabah", "I drink coffee every morning.", "Her sabah kahve içerim.")),
            practice=listOf(PracticeQuestion("He ___ English every day.", listOf("study", "studies", "studying", "studied"), 1, "He ile fiil -s/-es alır.", "present simple"))
        ),
        Lesson(
            id="a1-daily", level=CefrLevel.A1, order=4, title="Günlük hayatta konuş", subtitle="Alışveriş, kafe, yol sorma", skill=SkillType.VOCAB,
            explanation="Günlük hayatta birkaç güçlü kalıp çok iş görür: I'd like..., How much...?, Where is...?, Can you help me?", simpleExplanation="Bir şeyi isterken I'd like... daha nazik ve doğaldır.",
            examples=listOf(ExampleLine("I'd like a coffee, please.", "Bir kahve istiyorum, lütfen."), ExampleLine("How much is this?", "Bu ne kadar?"), ExampleLine("Where is the station?", "İstasyon nerede?")),
            vocabulary=listOf(VocabItem("price", "fiyat", "I'd like a coffee, please.", "Bir kahve istiyorum, lütfen."), VocabItem("coffee", "kahve", "I'd like a coffee, please.", "Bir kahve istiyorum, lütfen."), VocabItem("station", "istasyon", "Where is the station?", "İstasyon nerede?")),
            practice=listOf(PracticeQuestion("Nazikçe kahve istemek için?", listOf("Give coffee", "I'd like a coffee, please", "Coffee me", "I coffee"), 1, "I'd like... nazik istek kalıbıdır.", "daily English"))
        ),
        Lesson(
            id="a2-past", level=CefrLevel.A2, order=1, title="Geçmişi anlat", subtitle="Past Simple", skill=SkillType.GRAMMAR,
            explanation="Past Simple geçmişte başlayıp bitmiş olaylar içindir. Düzenli fiiller -ed alır; go→went gibi düzensiz fiiller ayrıca öğrenilir.", simpleExplanation="Dün oldu ve bitti → Past Simple.",
            examples=listOf(ExampleLine("I watched a movie last night.", "Dün gece film izledim."), ExampleLine("We went to the beach.", "Plaja gittik."), ExampleLine("She didn't call me.", "Beni aramadı.")),
            vocabulary=listOf(VocabItem("yesterday", "dün", "I watched a movie last night.", "Dün gece film izledim."), VocabItem("went", "gitti", "We went to the beach.", "Plaja gittik."), VocabItem("saw", "gördü", "I watched a movie last night.", "Dün gece film izledim.")),
            practice=listOf(PracticeQuestion("Yesterday I ___ to work.", listOf("go", "went", "gone", "going"), 1, "Go fiilinin geçmiş hali went’tir.", "past simple"))
        ),
        Lesson(
            id="a2-future", level=CefrLevel.A2, order=2, title="Gelecekten bahset", subtitle="will / going to", skill=SkillType.GRAMMAR,
            explanation="Going to çoğunlukla plan ve niyet; will ise anlık karar, teklif ve tahminlerde sık görülür.", simpleExplanation="Plan hazırsa going to; o anda karar verdiysen will iyi bir başlangıç kuralıdır.",
            examples=listOf(ExampleLine("I'm going to study tonight.", "Bu gece ders çalışacağım."), ExampleLine("I'll help you.", "Sana yardım edeceğim."), ExampleLine("It will probably rain.", "Muhtemelen yağmur yağacak.")),
            vocabulary=listOf(VocabItem("plan", "plan", "I'm going to study tonight.", "Bu gece ders çalışacağım."), VocabItem("tonight", "bu gece", "I'm going to study tonight.", "Bu gece ders çalışacağım."), VocabItem("probably", "muhtemelen", "It will probably rain.", "Muhtemelen yağmur yağacak.")),
            practice=listOf(PracticeQuestion("Önceden plan: I ___ visit London.", listOf("am going to", "did", "was", "have"), 0, "Plan için going to uygundur.", "future"))
        ),
        Lesson(
            id="a2-travel", level=CefrLevel.A2, order=3, title="Seyahat İngilizcesi", subtitle="Havaalanı, otel, yol tarifi", skill=SkillType.VOCAB,
            explanation="Seyahatte amaç mükemmel gramer değil; ihtiyacını net söylemektir. Reservation, gate, luggage ve directions çok işe yarar.", simpleExplanation="Kısa ve net konuş: I have a reservation. Where is gate 12?",
            examples=listOf(ExampleLine("I have a reservation.", "Rezervasyonum var."), ExampleLine("Where is the boarding gate?", "Biniş kapısı nerede?"), ExampleLine("Could you show me on the map?", "Haritada gösterebilir misiniz?")),
            vocabulary=listOf(VocabItem("reservation", "rezervasyon", "I have a reservation.", "Rezervasyonum var."), VocabItem("luggage", "bagaj", "I have a reservation.", "Rezervasyonum var."), VocabItem("gate", "kapı", "Where is the boarding gate?", "Biniş kapısı nerede?")),
            practice=listOf(PracticeQuestion("Rezervasyonum var. hangisi?", listOf("I have a reservation", "I am reservation", "Reservation me", "I do reservation"), 0, "Doğal kalıp I have a reservation.", "travel"))
        ),
        Lesson(
            id="a2-smalltalk", level=CefrLevel.A2, order=4, title="Small talk", subtitle="Kısa sohbeti sürdür", skill=SkillType.SPEAKING,
            explanation="Doğal konuşma sadece soru-cevap değildir. Kısa tepki verip karşı soru sorarsan sohbet sürer.", simpleExplanation="Cevap ver + küçük tepki + karşı soru: Really? That sounds nice. What about you?",
            examples=listOf(ExampleLine("How was your weekend?", "Hafta sonun nasıldı?"), ExampleLine("That sounds great!", "Kulağa harika geliyor!"), ExampleLine("What about you?", "Ya sen?")),
            vocabulary=listOf(VocabItem("weekend", "hafta sonu", "How was your weekend?", "Hafta sonun nasıldı?"), VocabItem("really", "gerçekten", "How was your weekend?", "Hafta sonun nasıldı?"), VocabItem("sounds", "kulağa geliyor", "That sounds great!", "Kulağa harika geliyor!")),
            practice=listOf(PracticeQuestion("Sohbeti sürdürmek için?", listOf("Okay.", "What about you?", "Stop.", "No talk."), 1, "Karşı soru sohbeti devam ettirir.", "small talk"))
        ),
        Lesson(
            id="b1-perfect", level=CefrLevel.B1, order=1, title="Present Perfect", subtitle="Geçmişle bugünü bağla", skill=SkillType.GRAMMAR,
            explanation="Present Perfect deneyimler, sonucu şimdi önemli olan olaylar ve zamanı belirtilmeyen geçmiş için sık kullanılır: have/has + V3.", simpleExplanation="Zaman değil deneyim/sonuç önemliyse Present Perfect düşün.",
            examples=listOf(ExampleLine("I've visited Rome twice.", "Roma’yı iki kez ziyaret ettim."), ExampleLine("She has just arrived.", "Daha yeni geldi."), ExampleLine("Have you ever tried sushi?", "Hiç suşi denedin mi?")),
            vocabulary=listOf(VocabItem("ever", "hiç", "Have you ever tried sushi?", "Hiç suşi denedin mi?"), VocabItem("already", "çoktan", "I've visited Rome twice.", "Roma’yı iki kez ziyaret ettim."), VocabItem("yet", "henüz", "I've visited Rome twice.", "Roma’yı iki kez ziyaret ettim.")),
            practice=listOf(PracticeQuestion("Have you ever ___ to Italy?", listOf("go", "went", "been", "going"), 2, "Present Perfect: have + V3; be→been.", "present perfect"))
        ),
        Lesson(
            id="b1-opinions", level=CefrLevel.B1, order=2, title="Fikir belirt ve savun", subtitle="because, for example, however", skill=SkillType.SPEAKING,
            explanation="B1 seviyesinde tek kelimelik fikir yerine görüş + gerekçe + örnek hedeflenir.", simpleExplanation="I think... because... For example... kalıbı konuşmayı büyütür.",
            examples=listOf(ExampleLine("In my opinion, remote work is useful.", "Bence uzaktan çalışma faydalı."), ExampleLine("I see your point, but I disagree.", "Ne demek istediğini anlıyorum ama katılmıyorum."), ExampleLine("For example, I can focus better at home.", "Örneğin evde daha iyi odaklanıyorum.")),
            vocabulary=listOf(VocabItem("opinion", "görüş", "In my opinion, remote work is useful.", "Bence uzaktan çalışma faydalı."), VocabItem("agree", "katılmak", "I see your point, but I disagree.", "Ne demek istediğini anlıyorum ama katılmıyorum."), VocabItem("disagree", "katılmamak", "I see your point, but I disagree.", "Ne demek istediğini anlıyorum ama katılmıyorum.")),
            practice=listOf(PracticeQuestion("Nazikçe katılmamak için?", listOf("You are wrong.", "I see your point, but I disagree.", "No.", "Bad idea."), 1, "Bu ifade görüş ayrılığını nazikçe söyler.", "opinions"))
        ),
        Lesson(
            id="b1-conditionals", level=CefrLevel.B1, order=3, title="If cümleleri", subtitle="Gerçek ihtimal ve sonuç", skill=SkillType.GRAMMAR,
            explanation="First Conditional gelecekte gerçekçi bir ihtimal için kullanılır: if + present, will + verb.", simpleExplanation="If it rains, I will stay home. = Yağmur yağarsa evde kalacağım.",
            examples=listOf(ExampleLine("If I have time, I'll call you.", "Vaktim olursa seni arayacağım."), ExampleLine("If it rains, we will stay inside.", "Yağmur yağarsa içeride kalacağız."), ExampleLine("You will improve if you practise.", "Pratik yaparsan gelişeceksin.")),
            vocabulary=listOf(VocabItem("if", "eğer", "If I have time, I'll call you.", "Vaktim olursa seni arayacağım."), VocabItem("improve", "gelişmek", "You will improve if you practise.", "Pratik yaparsan gelişeceksin."), VocabItem("practise", "pratik yapmak", "You will improve if you practise.", "Pratik yaparsan gelişeceksin.")),
            practice=listOf(PracticeQuestion("If I study, I ___ improve.", listOf("will", "did", "was", "have"), 0, "First conditional sonucu genellikle will ile kurulur.", "conditionals"))
        ),
        Lesson(
            id="b1-phrasal", level=CefrLevel.B1, order=4, title="Phrasal verbs", subtitle="Gerçek konuşmada çok geçen fiiller", skill=SkillType.VOCAB,
            explanation="Phrasal verb, fiil + parçacıkla yeni anlam kazanır. Give up, find out, pick up gibi yapıları tek kelime gibi öğren.", simpleExplanation="give up = vazgeçmek. Kelimeleri ayrı ayrı çevirmeye çalışma.",
            examples=listOf(ExampleLine("Don't give up.", "Vazgeçme."), ExampleLine("I found out the truth.", "Gerçeği öğrendim."), ExampleLine("Can you pick me up?", "Beni alabilir misin?")),
            vocabulary=listOf(VocabItem("give up", "vazgeçmek", "Don't give up.", "Vazgeçme."), VocabItem("find out", "öğrenmek", "Don't give up.", "Vazgeçme."), VocabItem("pick up", "alıp götürmek", "Can you pick me up?", "Beni alabilir misin?")),
            practice=listOf(PracticeQuestion("Vazgeçmek hangisi?", listOf("give up", "take off", "look at", "turn on"), 0, "give up = vazgeçmek.", "phrasal verbs"))
        ),
        Lesson(
            id="b2-natural", level=CefrLevel.B2, order=1, title="Daha doğal konuş", subtitle="actually, apparently, basically", skill=SkillType.VOCAB,
            explanation="B2’de küçük discourse marker’lar konuşmayı kitap İngilizcesinden çıkarıp daha doğal yapar.", simpleExplanation="Küçük bağlayıcılarla düşünceni doğal biçimde yönlendir.",
            examples=listOf(ExampleLine("Actually, I changed my mind.", "Aslında fikrimi değiştirdim."), ExampleLine("Apparently, the meeting was cancelled.", "Görünüşe göre toplantı iptal edilmiş."), ExampleLine("Basically, we need more time.", "Kısaca daha fazla zamana ihtiyacımız var.")),
            vocabulary=listOf(VocabItem("actually", "aslında", "Actually, I changed my mind.", "Aslında fikrimi değiştirdim."), VocabItem("apparently", "görünüşe göre", "Apparently, the meeting was cancelled.", "Görünüşe göre toplantı iptal edilmiş."), VocabItem("basically", "kısaca/temelde", "Basically, we need more time.", "Kısaca daha fazla zamana ihtiyacımız var.")),
            practice=listOf(PracticeQuestion("Görünüşe göre hangisi?", listOf("Actually", "Apparently", "Exactly", "Mostly"), 1, "Apparently = görünüşe göre.", "discourse markers"))
        ),
        Lesson(
            id="b2-passive", level=CefrLevel.B2, order=2, title="Passive Voice", subtitle="Yapan değil olay önemliyse", skill=SkillType.GRAMMAR,
            explanation="Passive, eylemi yapan bilinmediğinde veya önemli olmadığında kullanılır: be + V3.", simpleExplanation="Active: They built it. Passive: It was built.",
            examples=listOf(ExampleLine("The bridge was built in 1990.", "Köprü 1990’da inşa edildi."), ExampleLine("The results will be announced tomorrow.", "Sonuçlar yarın açıklanacak."), ExampleLine("English is spoken worldwide.", "İngilizce dünya çapında konuşulur.")),
            vocabulary=listOf(VocabItem("built", "inşa edilmiş", "The bridge was built in 1990.", "Köprü 1990’da inşa edildi."), VocabItem("announced", "açıklanmış", "The results will be announced tomorrow.", "Sonuçlar yarın açıklanacak."), VocabItem("worldwide", "dünya çapında", "English is spoken worldwide.", "İngilizce dünya çapında konuşulur.")),
            practice=listOf(PracticeQuestion("Sonuçlar açıklandı. hangisi?", listOf("The results announced", "The results were announced", "They results announce", "Results did announce"), 1, "Past passive: was/were + V3.", "passive voice"))
        ),
        Lesson(
            id="b2-debate", level=CefrLevel.B2, order=3, title="Tartış ve gerekçelendir", subtitle="Karşı görüşü de yönet", skill=SkillType.SPEAKING,
            explanation="B2 düzeyinde fikri savunurken karşı görüşü tanıyıp gerekçe sunmak önemlidir.", simpleExplanation="Although..., I would argue that... / On the other hand... kalıplarını kullan.",
            examples=listOf(ExampleLine("Although it's expensive, I think it's worth it.", "Pahalı olsa da buna değdiğini düşünüyorum."), ExampleLine("On the other hand, there are some risks.", "Öte yandan bazı riskler var."), ExampleLine("The benefits outweigh the drawbacks.", "Faydaları dezavantajlarından ağır basıyor.")),
            vocabulary=listOf(VocabItem("although", "-e rağmen", "Although it's expensive, I think it's worth it.", "Pahalı olsa da buna değdiğini düşünüyorum."), VocabItem("drawback", "dezavantaj", "The benefits outweigh the drawbacks.", "Faydaları dezavantajlarından ağır basıyor."), VocabItem("outweigh", "ağır basmak", "The benefits outweigh the drawbacks.", "Faydaları dezavantajlarından ağır basıyor.")),
            practice=listOf(PracticeQuestion("Karşı görüşe geçiş için?", listOf("On the other hand", "Yesterday", "At first", "For sale"), 0, "On the other hand zıt bakış açısına geçiş sağlar.", "argumentation"))
        ),
        Lesson(
            id="b2-collocations", level=CefrLevel.B2, order=4, title="Collocations", subtitle="Doğal kelime eşleşmeleri", skill=SkillType.VOCAB,
            explanation="İleri konuşmada doğru kelime yetmez, doğal eşleşme gerekir: make a decision, take responsibility, heavy rain.", simpleExplanation="Kelimeyi tek başına değil, yanında gelen kelimeyle öğren.",
            examples=listOf(ExampleLine("I need to make a decision.", "Karar vermem gerekiyor."), ExampleLine("She took responsibility.", "Sorumluluğu üstlendi."), ExampleLine("There was heavy rain.", "Şiddetli yağmur vardı.")),
            vocabulary=listOf(VocabItem("make a decision", "karar vermek", "I need to make a decision.", "Karar vermem gerekiyor."), VocabItem("take responsibility", "sorumluluk almak", "I need to make a decision.", "Karar vermem gerekiyor."), VocabItem("heavy rain", "şiddetli yağmur", "There was heavy rain.", "Şiddetli yağmur vardı.")),
            practice=listOf(PracticeQuestion("Doğal eşleşme hangisi?", listOf("do a decision", "make a decision", "create decision", "build a decision"), 1, "Doğal kalıp make a decision’dır.", "collocations"))
        ),
        Lesson(
            id="c1-hedging", level=CefrLevel.C1, order=1, title="Kesinliği yumuşat", subtitle="Hedging ve nüans", skill=SkillType.SPEAKING,
            explanation="C1’de her şeyi kesin söylemek yerine olasılık ve temkin dereceleri kullanılır: tends to, appears to, arguably, to some extent.", simpleExplanation="It is yerine bazen It appears to be diyerek tonu yumuşat.",
            examples=listOf(ExampleLine("This tends to happen in large cities.", "Bu büyük şehirlerde olma eğilimindedir."), ExampleLine("It appears to be effective.", "Etkili görünüyor."), ExampleLine("To some extent, I agree.", "Bir ölçüde katılıyorum.")),
            vocabulary=listOf(VocabItem("arguably", "denebilir ki", "This tends to happen in large cities.", "Bu büyük şehirlerde olma eğilimindedir."), VocabItem("tends to", "eğilimindedir", "This tends to happen in large cities.", "Bu büyük şehirlerde olma eğilimindedir."), VocabItem("to some extent", "bir ölçüde", "This tends to happen in large cities.", "Bu büyük şehirlerde olma eğilimindedir.")),
            practice=listOf(PracticeQuestion("En temkinli ifade?", listOf("It is definitely true", "It appears to be true", "It must be true", "It is absolutely true"), 1, "appears to be kesinliği yumuşatır.", "hedging"))
        ),
        Lesson(
            id="c1-register", level=CefrLevel.C1, order=2, title="Resmî mi günlük mü?", subtitle="Register ve ton", skill=SkillType.WRITING,
            explanation="Bağlama göre dil tonu değişir. Arkadaşına Can you...? derken resmî e-postada I would appreciate it if... kullanılabilir.", simpleExplanation="Kime konuştuğunu düşün; kelimeleri ona göre seç.",
            examples=listOf(ExampleLine("Could you send me the file?", "Dosyayı gönderebilir misin?"), ExampleLine("I would appreciate it if you could send the file.", "Dosyayı gönderebilirseniz memnun olurum."), ExampleLine("Hey, can you send that over?", "Hey, şunu gönderir misin?")),
            vocabulary=listOf(VocabItem("appreciate", "memnun olmak/takdir etmek", "I would appreciate it if you could send the file.", "Dosyayı gönderebilirseniz memnun olurum."), VocabItem("regarding", "ile ilgili", "Could you send me the file?", "Dosyayı gönderebilir misin?"), VocabItem("furthermore", "ayrıca", "Could you send me the file?", "Dosyayı gönderebilir misin?")),
            practice=listOf(PracticeQuestion("Resmî e-postaya en uygun?", listOf("Send it.", "Hey, send that.", "I would appreciate it if you could send the document.", "Gimme the file."), 2, "Bu yapı resmî ve naziktir.", "register"))
        ),
        Lesson(
            id="c1-idioms", level=CefrLevel.C1, order=3, title="Deyimler ve doğal ifadeler", subtitle="Kelimesi kelimesine çevrilmeyen dil", skill=SkillType.VOCAB,
            explanation="İdiomları bağlam içinde öğrenmek gerekir. Doğru yerde kullanıldığında doğal akıcılık sağlar.", simpleExplanation="break the ice = ortamı yumuşatmak. Birebir çevirmeye çalışma.",
            examples=listOf(ExampleLine("A joke helped break the ice.", "Bir şaka ortamı yumuşattı."), ExampleLine("Let's call it a day.", "Bugünlük bu kadar diyelim."), ExampleLine("We're on the same page.", "Aynı fikirdeyiz.")),
            vocabulary=listOf(VocabItem("break the ice", "ortamı yumuşatmak", "A joke helped break the ice.", "Bir şaka ortamı yumuşattı."), VocabItem("call it a day", "bugünlük bırakmak", "Let's call it a day.", "Bugünlük bu kadar diyelim."), VocabItem("on the same page", "aynı fikirde olmak", "We're on the same page.", "Aynı fikirdeyiz.")),
            practice=listOf(PracticeQuestion("Bugünlük bırakalım deyimi?", listOf("Break the ice", "Call it a day", "Hit the road", "Make sense"), 1, "call it a day = bugünlük sonlandırmak.", "idioms"))
        ),
        Lesson(
            id="c1-complex", level=CefrLevel.C1, order=4, title="Karmaşık fikirleri bağla", subtitle="despite, whereas, consequently", skill=SkillType.WRITING,
            explanation="İleri seviyede uzun cümle değil, fikirler arasındaki ilişki önemlidir. Bağlayıcıları anlam ilişkisine göre seç.", simpleExplanation="Zıtlık mı, neden mi, sonuç mu? Önce ilişkiyi seç, sonra bağlayıcıyı.",
            examples=listOf(ExampleLine("Despite the cost, demand remains high.", "Maliyete rağmen talep yüksek kalıyor."), ExampleLine("Whereas one option is cheaper, the other is more reliable.", "Biri daha ucuzken diğeri daha güvenilir."), ExampleLine("Consequently, the project was delayed.", "Sonuç olarak proje gecikti.")),
            vocabulary=listOf(VocabItem("despite", "-e rağmen", "Despite the cost, demand remains high.", "Maliyete rağmen talep yüksek kalıyor."), VocabItem("whereas", "oysa/-iken", "Whereas one option is cheaper, the other is more reliable.", "Biri daha ucuzken diğeri daha güvenilir."), VocabItem("consequently", "sonuç olarak", "Consequently, the project was delayed.", "Sonuç olarak proje gecikti.")),
            practice=listOf(PracticeQuestion("Sonuç bildiren bağlayıcı?", listOf("Whereas", "Despite", "Consequently", "Although"), 2, "Consequently = sonuç olarak.", "linkers"))
        ),
        Lesson(
            id="c2-subtle", level=CefrLevel.C2, order=1, title="İnce anlam farkları", subtitle="Çağrışım ve yakın anlamlılar", skill=SkillType.VOCAB,
            explanation="C2 seviyesinde benzer görünen kelimelerin ton ve çağrışım farklarını ayırt etmek önemlidir.", simpleExplanation="Sadece anlamı değil, kelimenin hissettirdiği tonu da öğren.",
            examples=listOf(ExampleLine("She is slim.", "O ince yapılı."), ExampleLine("He looks skinny.", "O cılız görünüyor."), ExampleLine("The proposal is ambitious rather than unrealistic.", "Teklif gerçek dışı olmaktan ziyade iddialı.")),
            vocabulary=listOf(VocabItem("subtle", "ince/belirsiz", "She is slim.", "O ince yapılı."), VocabItem("ambitious", "iddialı", "The proposal is ambitious rather than unrealistic.", "Teklif gerçek dışı olmaktan ziyade iddialı."), VocabItem("connotation", "çağrışım", "She is slim.", "O ince yapılı.")),
            practice=listOf(PracticeQuestion("Connotation neyi anlatır?", listOf("Sadece sözlük anlamını", "Kelimenin çağrıştırdığı ek anlamı", "Yazımını", "Çoğulunu"), 1, "Connotation kelimenin duygu ve çağrışım katmanıdır.", "semantics"))
        ),
        Lesson(
            id="c2-pragmatics", level=CefrLevel.C2, order=2, title="Aslında ne demek istedi?", subtitle="İma, ironi ve nezaket", skill=SkillType.LISTENING,
            explanation="C2 düzeyinde kelimelerin sözlük anlamından çok bağlamdaki işlevi önem kazanır.", simpleExplanation="Cümleden önce bağlama, ses tonuna ve ilişkiye bak.",
            examples=listOf(ExampleLine("That's... interesting.", "Bu... ilginç. (tona göre mesafeli olabilir)"), ExampleLine("You might want to reconsider that.", "Bunu yeniden düşünmek isteyebilirsin."), ExampleLine("With all due respect, I disagree.", "Tüm saygımla, katılmıyorum.")),
            vocabulary=listOf(VocabItem("reconsider", "yeniden değerlendirmek", "You might want to reconsider that.", "Bunu yeniden düşünmek isteyebilirsin."), VocabItem("with all due respect", "tüm saygımla", "With all due respect, I disagree.", "Tüm saygımla, katılmıyorum."), VocabItem("irony", "ironi", "That's... interesting.", "Bu... ilginç. (tona göre mesafeli olabilir)")),
            practice=listOf(PracticeQuestion("Kibar uyarı hangisi?", listOf("You are totally wrong.", "You might want to reconsider that.", "Bad choice.", "Never do that."), 1, "might want to yapısı uyarıyı yumuşatır.", "pragmatics"))
        ),
        Lesson(
            id="c2-style", level=CefrLevel.C2, order=3, title="Stil dönüşümü", subtitle="Aynı fikri farklı tonda söyle", skill=SkillType.WRITING,
            explanation="C2’de aynı içeriği kısa, resmî, ikna edici, diplomatik veya gündelik biçimde yeniden yazabilmek önemlidir.", simpleExplanation="Aynı fikir, farklı ortamda farklı cümle olmalı.",
            examples=listOf(ExampleLine("We need to fix this.", "Bunu düzeltmemiz gerekiyor."), ExampleLine("This issue requires prompt attention.", "Bu konu hızlı müdahale gerektiriyor."), ExampleLine("We should probably sort this out soon.", "Muhtemelen bunu yakında halletmeliyiz.")),
            vocabulary=listOf(VocabItem("prompt", "gecikmeden/hızlı", "This issue requires prompt attention.", "Bu konu hızlı müdahale gerektiriyor."), VocabItem("sort out", "halletmek", "We should probably sort this out soon.", "Muhtemelen bunu yakında halletmeliyiz."), VocabItem("require", "gerektirmek", "This issue requires prompt attention.", "Bu konu hızlı müdahale gerektiriyor.")),
            practice=listOf(PracticeQuestion("En resmî seçenek?", listOf("Fix it now.", "Let’s sort this out.", "This issue requires prompt attention.", "We gotta fix it."), 2, "requires prompt attention resmî tona uygundur.", "style"))
        ),
        Lesson(
            id="c2-mastery", level=CefrLevel.C2, order=4, title="Akıcılık ustalığı", subtitle="Yeniden ifade et ve tonu yönet", skill=SkillType.SPEAKING,
            explanation="C2 hedefi nadir kelime kullanmak değil; fikri dinleyiciye göre uyarlamak ve anlaşılmayınca anında farklı biçimde söylemektir.", simpleExplanation="Bir cümleyi söyleyemiyorsan daha basit yoldan söyle. Akıcılık budur.",
            examples=listOf(ExampleLine("Let me put that another way.", "Bunu başka şekilde ifade edeyim."), ExampleLine("To put it more simply, we need a backup plan.", "Daha basit söylersek yedek plana ihtiyacımız var."), ExampleLine("Perhaps I should clarify what I mean.", "Belki ne demek istediğimi açıklamalıyım.")),
            vocabulary=listOf(VocabItem("clarify", "açıklığa kavuşturmak", "Perhaps I should clarify what I mean.", "Belki ne demek istediğimi açıklamalıyım."), VocabItem("rephrase", "yeniden ifade etmek", "Let me put that another way.", "Bunu başka şekilde ifade edeyim."), VocabItem("flexibility", "esneklik", "Let me put that another way.", "Bunu başka şekilde ifade edeyim.")),
            practice=listOf(PracticeQuestion("Anlaşılmayınca ne denir?", listOf("No understand.", "Let me put that another way.", "Forget it always.", "English finish."), 1, "Bu ifade aynı fikri farklı biçimde açıklamaya geçer.", "communication strategy"))
        )
    )
    fun forLevel(level: CefrLevel): List<Lesson> = lessons.filter { it.level == level }.sortedBy { it.order }
}