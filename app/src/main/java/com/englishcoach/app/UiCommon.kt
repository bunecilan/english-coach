package com.englishcoach.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Ink = Color(0xFF0E1021)
val Panel = Color(0xFF171A33)
val Panel2 = Color(0xFF202544)
val Purple = Color(0xFF8B5CF6)
val Pink = Color(0xFFFF5FA2)
val Mint = Color(0xFF50E3C2)
val Sky = Color(0xFF58C7FF)
val Gold = Color(0xFFFFC857)
val SoftText = Color(0xFFB9BEDA)
val Success = Color(0xFF54D68C)
val Danger = Color(0xFFFF6B7A)

val CardShape = RoundedCornerShape(24)
val SmallShape = RoundedCornerShape(16)

@Composable
fun EnglishCoachTheme(content: @Composable () -> Unit) {
    val scheme = darkColorScheme(
        primary = Purple,
        secondary = Mint,
        tertiary = Pink,
        background = Ink,
        surface = Panel,
        onPrimary = Color.White,
        onBackground = Color.White,
        onSurface = Color.White
    )
    MaterialTheme(colorScheme = scheme, content = content)
}

@Composable
fun GradientBackground(content: @Composable () -> Unit) {
    Box(
        Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF0E1021), Color(0xFF12152A), Color(0xFF19142B))
                )
            )
    ) { content() }
}

fun levelColor(level: CefrLevel): Color = when (level) {
    CefrLevel.A1 -> Color(0xFF4FD1C5)
    CefrLevel.A2 -> Color(0xFF58C7FF)
    CefrLevel.B1 -> Color(0xFF8B5CF6)
    CefrLevel.B2 -> Color(0xFFFF5FA2)
    CefrLevel.C1 -> Color(0xFFFFA94D)
    CefrLevel.C2 -> Color(0xFFFFD166)
}
