package com.lostastra.duroodcounter.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = md_primary,
    onPrimary = md_onPrimary,
    background = md_background,
    onBackground = md_onBackground,
    surface = md_background,
    onSurface = md_onBackground
)

@Composable
fun DuroodCounterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography,
        content = content
    )
}
