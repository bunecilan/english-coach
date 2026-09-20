package com.englishcoach.app

object SpeakingContent {
    val scenarios = listOf(
        SpeakingScenario(CefrLevel.A1, "Kendini tanıt", "Yeni tanıştığın birine kendinden bahset.",
            listOf("What is your name?", "Where are you from?", "What do you do?"),
            listOf("My name is Ece.", "I'm from Turkey.", "I work in an office.")),
        SpeakingScenario(CefrLevel.A1, "Kafede sipariş ver", "Bir kafede içecek ve yiyecek iste.",
            listOf("What would you like?", "Anything else?", "Cash or card?"),
            listOf("I'd like a coffee, please.", "Yes, a sandwich too.", "By card, please.")),
        SpeakingScenario(CefrLevel.A2, "Otel check-in", "Resepsiyonda rezervasyonunu anlat.",
            listOf("Do you have a reservation?", "How many nights are you staying?", "Would you like breakfast?"),
            listOf("Yes, it's under the name Kaya.", "I'm staying for three nights.", "Yes, please.")),
        SpeakingScenario(CefrLevel.A2, "Hafta sonunu anlat", "Geçmişte ne yaptığını kısa şekilde anlat.",
            listOf("How was your weekend?", "What did you do?", "Did you enjoy it?"),
            listOf("It was great.", "I met some friends and watched a movie.", "Yes, I really enjoyed it.")),
        SpeakingScenario(CefrLevel.B1, "Bir fikri savun", "Uzaktan çalışma hakkında görüş belirt.",
            listOf("Do you prefer working from home?", "Why?", "Is there any disadvantage?"),
            listOf("I prefer it in some situations.", "It saves commuting time and helps me focus.", "The main disadvantage is less face-to-face contact.")),
        SpeakingScenario(CefrLevel.B1, "Bir sorun çöz", "Rezervasyonunda hata var; görevliyle konuş.",
            listOf("What seems to be the problem?", "What did you book?", "What would you like us to do?"),
            listOf("I think there's a mistake with my booking.", "I booked a double room for two nights.", "Could you check the reservation again, please?")),
        SpeakingScenario(CefrLevel.B2, "Tartışmaya katıl", "Sosyal medyanın fayda ve zararlarını dengeli anlat.",
            listOf("Is social media mostly positive?", "What are the main benefits?", "What are the risks?"),
            listOf("It depends on how it's used.", "It makes communication and access to information easier.", "On the other hand, misinformation and excessive use are real concerns.")),
        SpeakingScenario(CefrLevel.B2, "İş görüşmesi", "Deneyimini doğal biçimde anlat.",
            listOf("Tell me about yourself.", "Describe a difficult situation you handled.", "Why are you interested in this role?"),
            listOf("I've worked in customer-facing roles for several years.", "I once had to solve a last-minute delivery problem by coordinating two teams.", "The role matches both my experience and the skills I want to develop.")),
        SpeakingScenario(CefrLevel.C1, "Diplomatik itiraz", "Toplantıda bir fikre katılmıyorsun ama tonu koru.",
            listOf("Do you agree with the proposal?", "What concerns do you have?", "What would you suggest instead?"),
            listOf("I can see the logic behind it, but I have some reservations.", "My main concern is the timeline, which seems rather optimistic.", "I would suggest a phased approach so we can evaluate the results as we go.")),
        SpeakingScenario(CefrLevel.C1, "Soyut konu", "Başarı ile şans arasındaki ilişkiyi tartış.",
            listOf("How important is luck in success?", "Can hard work compensate for bad luck?", "Give an example."),
            listOf("Luck can influence opportunity, but it rarely explains the whole outcome.", "Hard work cannot control every external factor, yet it can improve the chances of making use of an opportunity.", "For instance, preparation may determine whether someone can benefit from an unexpected opening.")),
        SpeakingScenario(CefrLevel.C2, "Nüanslı tartışma", "Teknolojik ilerlemenin her zaman toplumsal ilerleme anlamına gelip gelmediğini tartış.",
            listOf("Does technological progress equal social progress?", "What makes the relationship complicated?", "How would you summarise your view?"),
            listOf("Not necessarily; technology expands what is possible, but societies still decide how those possibilities are distributed and governed.", "The benefits and costs are rarely shared evenly, and new capabilities can create new forms of dependence.", "I'd say technology is an amplifier rather than a guarantee of progress.")),
        SpeakingScenario(CefrLevel.C2, "Ton değiştir", "Aynı fikri arkadaşına ve resmî toplantıda farklı ifade et.",
            listOf("Tell a friend the plan is unrealistic.", "Now say it diplomatically in a meeting.", "Now soften it further."),
            listOf("Honestly, I don't think this plan is going to work.", "I have some concerns about whether the current plan is realistic.", "It may be worth reconsidering some of the assumptions behind the current timeline."))
    )

    fun forLevel(level: CefrLevel) = scenarios.filter { it.level == level }
}
