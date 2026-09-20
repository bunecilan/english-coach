package com.englishcoach.app

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoStories
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

sealed interface AppRoute {
    data object Home : AppRoute
    data object Learn : AppRoute
    data object Stories : AppRoute
    data object Practice : AppRoute
    data object Profile : AppRoute
    data class LessonDetail(val lesson: Lesson) : AppRoute
    data class StoryDetail(val story: Story) : AppRoute
    data object Speaking : AppRoute
    data object Vocabulary : AppRoute
    data object Yds : AppRoute
    data object YdsQuiz : AppRoute
}

@Composable
fun EnglishCoachApp() {
    EnglishCoachTheme {
        val context = LocalContext.current
        val store = remember { LearningStore(context) }
        var route by remember { mutableStateOf<AppRoute>(AppRoute.Home) }
        var revision by remember { mutableIntStateOf(0) }

        val mainRoute = route is AppRoute.Home || route is AppRoute.Learn || route is AppRoute.Stories || route is AppRoute.Practice || route is AppRoute.Profile
        GradientBackground {
            Scaffold(
                containerColor = Color.Transparent,
                bottomBar = {
                    if (mainRoute) {
                        NavigationBar(containerColor = Color(0xEE15182D)) {
                            navItems().forEach { item ->
                                NavigationBarItem(
                                    selected = route::class == item.route::class,
                                    onClick = { route = item.route },
                                    icon = { Icon(item.icon, item.label) },
                                    label = { Text(item.label) }
                                )
                            }
                        }
                    }
                }
            ) { pad ->
                val mod = Modifier.padding(pad)
                when (val r = route) {
                    AppRoute.Home -> HomeScreen(store, revision, mod,
                        onLearn = { route = AppRoute.Learn },
                        onStories = { route = AppRoute.Stories },
                        onSpeaking = { route = AppRoute.Speaking },
                        onYds = { route = AppRoute.Yds },
                        onLesson = { route = AppRoute.LessonDetail(it) }
                    )
                    AppRoute.Learn -> LearnScreen(store, revision, mod,
                        onLevelChanged = { store.selectedLevel = it; revision++ },
                        onLesson = { route = AppRoute.LessonDetail(it) }
                    )
                    AppRoute.Stories -> StoriesScreen(store, revision, mod,
                        onLevelChanged = { store.selectedLevel = it; revision++ },
                        onStory = { route = AppRoute.StoryDetail(it) }
                    )
                    AppRoute.Practice -> PracticeHubScreen(store, revision, mod,
                        onSpeaking = { route = AppRoute.Speaking },
                        onVocabulary = { route = AppRoute.Vocabulary },
                        onYds = { route = AppRoute.Yds }
                    )
                    AppRoute.Profile -> ProfileScreen(store, revision, mod)
                    is AppRoute.LessonDetail -> LessonDetailScreen(r.lesson, store, mod,
                        onBack = { route = AppRoute.Learn },
                        onChanged = { revision++ }
                    )
                    is AppRoute.StoryDetail -> StoryDetailScreen(r.story, store, mod,
                        onBack = { route = AppRoute.Stories },
                        onChanged = { revision++ }
                    )
                    AppRoute.Speaking -> SpeakingPracticeScreen(store, mod,
                        onBack = { route = AppRoute.Practice },
                        onChanged = { revision++ }
                    )
                    AppRoute.Vocabulary -> VocabularyNotebookScreen(store, revision, mod,
                        onBack = { route = AppRoute.Practice },
                        onChanged = { revision++ }
                    )
                    AppRoute.Yds -> YdsHubScreen(mod,
                        onBack = { route = AppRoute.Practice },
                        onQuiz = { route = AppRoute.YdsQuiz }
                    )
                    AppRoute.YdsQuiz -> YdsQuizScreen(store, mod,
                        onBack = { route = AppRoute.Yds },
                        onChanged = { revision++ }
                    )
                }
            }
        }
    }
}

data class NavItem(val label: String, val icon: androidx.compose.ui.graphics.vector.ImageVector, val route: AppRoute)
private fun navItems() = listOf(
    NavItem("Ana Sayfa", Icons.Default.Home, AppRoute.Home),
    NavItem("Öğren", Icons.Default.MenuBook, AppRoute.Learn),
    NavItem("Hikâyeler", Icons.Default.AutoStories, AppRoute.Stories),
    NavItem("Pratik", Icons.Default.SportsEsports, AppRoute.Practice),
    NavItem("Profil", Icons.Default.Person, AppRoute.Profile)
)
