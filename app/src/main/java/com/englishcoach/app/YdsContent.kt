package com.englishcoach.app

object YdsContent {
    val tactics = listOf(
        YdsTactic(
            "Bağlaçları işaretle", "🔗",
            "YDS cümle ve paragraf sorularında anlam yönünü bağlaçlar belirler.",
            listOf(
                "Zıtlık: however, nevertheless, although, whereas, despite",
                "Neden-sonuç: therefore, thus, consequently, because, since",
                "Ek bilgi: moreover, furthermore, in addition",
                "Örnekleme: for example, for instance, such as"
            ),
            listOf("although + cümle", "despite + isim/V-ing", "therefore + sonuç", "whereas + karşıtlık")
        ),
        YdsTactic(
            "Kelimeyi tek başına değil kalıpla öğren", "🧠",
            "YDS'de kelimenin yanında hangi edat ve kelimenin geldiğini bilmek büyük avantaj sağlar.",
            listOf(
                "be responsible for",
                "result in / result from",
                "contribute to",
                "be associated with",
                "lead to",
                "depend on"
            ),
            listOf("play a crucial role in", "pose a threat to", "have an impact on", "take into account")
        ),
        YdsTactic(
            "Cümle tamamlama: önce ilişkiyi bul", "🧩",
            "Boşluğu doldurmadan önce iki taraf arasındaki anlam ilişkisini belirle.",
            listOf(
                "Zıtlık mı, sebep mi, sonuç mu?",
                "Zaman uyumunu kontrol et.",
                "Özne ve zamir referanslarını kontrol et.",
                "Şıklardaki bağlaçları ilk eleme aracı olarak kullan."
            ),
            listOf("not only ... but also", "either ... or", "neither ... nor", "whether ... or")
        ),
        YdsTactic(
            "Paragrafta ana fikir", "📖",
            "Her kelimeyi çevirmeye çalışma. Yazarın neyi savunduğunu veya açıkladığını bul.",
            listOf(
                "İlk ve son cümleleri dikkatle oku.",
                "Tekrarlanan kavramları yakala.",
                "Örnek ile ana fikri karıştırma.",
                "Aşırı kesin şıkları şüpheyle değerlendir: always, never, completely."
            ),
            listOf("the passage mainly discusses", "it can be inferred that", "the author suggests that")
        ),
        YdsTactic(
            "Çeviri: iskeleti kur", "🌍",
            "Önce özne + ana fiil + nesneyi bul, sonra yan cümleleri yerleştir.",
            listOf(
                "Relative clause: who/which/that",
                "Passive yapıyı kaçırma.",
                "Zamanı ana fiilden belirle.",
                "Türkçede doğal cümle kur; kelime kelime çeviri yapma."
            ),
            listOf("is believed to", "is likely to", "has been shown to", "in order to")
        ),
        YdsTactic(
            "Zaman yönetimi", "⏱️",
            "Tek soruda takılı kalmak toplam puanı düşürebilir.",
            listOf(
                "İlk turda net bildiklerini çöz.",
                "Uzun paragraf sorularını blok halinde çöz.",
                "İki şık arasında kaldığın soruları işaretle ve dön.",
                "Son bölüm için kontrol süresi bırak."
            ),
            listOf("eleme", "anahtar kelime", "referans kelimesi", "bağlaç yönü")
        )
    )

    val questions = listOf(
        PracticeQuestion(
            "Many cities are investing in public transport ___ traffic congestion can be reduced.",
            listOf("so that", "despite", "whereas", "unless"), 0,
            "Amaç bildiriliyor: 'trafik sıkışıklığı azaltılabilsin diye' → so that.", "YDS bağlaç"
        ),
        PracticeQuestion(
            "The new treatment has been shown ___ effective in reducing symptoms.",
            listOf("be", "to be", "being", "been"), 1,
            "Kalıp: has been shown to be.", "YDS kalıp"
        ),
        PracticeQuestion(
            "The project was delayed ___ unexpected technical problems.",
            listOf("because", "because of", "although", "therefore"), 1,
            "Sonrasında isim öbeği geldiği için because of kullanılır.", "YDS edat"
        ),
        PracticeQuestion(
            "Scientists are increasingly concerned about species ___ habitats are disappearing rapidly.",
            listOf("who", "whose", "where", "what"), 1,
            "Habitats, species'e ait olduğu için whose gerekir.", "YDS relative clause"
        ),
        PracticeQuestion(
            "Although the device is relatively expensive, demand for it remains high. Bu cümlenin ana ilişkisi nedir?",
            listOf("Neden", "Zıtlık", "Amaç", "Koşul"), 1,
            "Although zıtlık/ödünleme bildirir.", "YDS anlam ilişkisi"
        ),
        PracticeQuestion(
            "The study took several factors into ___ before reaching a conclusion.",
            listOf("account", "result", "effect", "cause"), 0,
            "Kalıp: take into account = dikkate almak.", "YDS collocation"
        ),
        PracticeQuestion(
            "If current trends continue, energy demand ___ significantly over the next decade.",
            listOf("rose", "will rise", "has risen", "would have risen"), 1,
            "Gerçek geleceğe ilişkin koşul: If + present, will + verb.", "YDS tense"
        ),
        PracticeQuestion(
            "The report suggests that the policy may have had an unintended ___ on small businesses.",
            listOf("impact", "permission", "arrival", "occasion"), 0,
            "Doğal kalıp: have an impact on.", "YDS vocabulary"
        ),
        PracticeQuestion(
            "___ the weather conditions were poor, the expedition continued as planned.",
            listOf("Because of", "Although", "Therefore", "In order to"), 1,
            "İki cümlecik arasında beklenmedik zıtlık var: Although.", "YDS bağlaç"
        ),
        PracticeQuestion(
            "A major advantage of online learning is that students can study at their own pace. 'at their own pace' ne demektir?",
            listOf("kendi hızlarında", "aynı anda", "öğretmensiz", "ücretsiz olarak"), 0,
            "at one's own pace = kendi hızında.", "YDS phrase"
        )
    )
}
