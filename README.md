# İngilizce Koçu — A1 → C2

Türkçe konuşan bir kullanıcının sıfırdan başlayıp gerçek hayatta İngilizce anlayıp konuşabilmesine odaklanan modern Android uygulaması.

## Ana sistem
- CEFR seviye seçimi: A1, A2, B1, B2, C1, C2
- Her seviyede kelime, gramer, konuşma, dinleme, okuma ve yazma hedefleri
- Kullanıcı seviyeyi istediği an değiştirebilir
- XP, günlük seri, ilerleme ve rozetler
- Konu bazlı doğru/yanlış geçmişine göre adaptif tekrar önerisi
- Kelime defteri

## İnteraktif hikâyeler
- A1 → C2 hikâye kütüphanesi
- İngilizce cümleye dokununca Türkçe çeviriyi göster/gizle
- Tüm Türkçeyi tek düğmeyle aç/kapat
- Cümleyi normal veya yavaş İngilizce dinleme
- Hikâyeden kelime kaydetme
- Okuduğunu anlama mini testleri
- The Lost Wallet ve My New City gibi aynı hikâyenin A1'den C2'ye büyüyen sürümleri

## Konuşma Laboratuvarı
- Seviyeye göre gerçek hayat senaryoları
- Sistem soruyu İngilizce seslendirir
- Örnek cevabı normal ve yavaş dinleme
- Kullanıcı mikrofona İngilizce söyler
- Android konuşma tanıma sonucu gösterilir
- Hedef cümle ile metinsel yakınlık puanı üretilir

## YDS / Sınav Merkezi
Ana öğrenme yolundan ayrıdır. Normal İngilizceyi sınava dönüştürmez.
- YDS bağlaç taktikleri
- Sık kelime ve collocation aileleri
- Cümle tamamlama stratejileri
- Paragraf ana fikir ve çıkarım taktikleri
- Çeviri iskeleti
- Zaman yönetimi
- 10 soruluk süreli mini deneme
- Geçmiş sınav soruları birebir kopyalanmaz; tekrar eden yapı ve soru mantıkları özgün örneklerle öğretilir.

## Teknik
- Kotlin
- Jetpack Compose
- Android 7.0+ (minSdk 24)
- target/compile SDK 35
- Java/Kotlin JVM 17
- AndroidX etkin
- Ders ve hikâye içerikleri çevrimdışı çalışır
- Konuşma tanıma özelliğinin çalışması cihazdaki Google/Android konuşma servislerine bağlı olabilir.

## GitHub ile APK üretme
`.github/workflows/build-apk.yml` GitHub Actions üzerinden debug APK üretir.
Artifact adı: `EnglishCoach-APK`
APK yolu: `app/build/outputs/apk/debug/app-debug.apk`
