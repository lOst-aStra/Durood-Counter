package com.lostastra.duroodcounter.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColors = darkColorScheme(
    primary = md_primary,
    onPrimary = md_onPrimary,
    background = md_background,
    onBackground = md_onBackground,
    surface = md_surface,
    onSurface = md_onBackground
)

@Composable
fun DuroodCounterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColors,
        typography = Typography,
        content = content
    )
}
