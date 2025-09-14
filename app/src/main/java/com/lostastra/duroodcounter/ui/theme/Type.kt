package com.lostastra.duroodcounter.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.lostastra.duroodcounter.R

// Font resources placed under: app/src/main/res/font/
// Files detected: Nunito-SemiBold.ttf, NotoNaskhArabic-SemiBold.ttf
// Resource IDs are derived as lowercase with underscores: nunito_semibold, notonaskharabic_semibold

private val Nunito = FontFamily(
    Font(R.font.nunito_semibold, weight = FontWeight.SemiBold)
)

private val NotoNaskhArabic = FontFamily(
    Font(R.font.notonaskharabic_semibold, weight = FontWeight.SemiBold)
)

// Public aliases for use across UI components
val LatinRoundedFontFamily: FontFamily = Nunito
val ArabicFontFamily: FontFamily = NotoNaskhArabic

// Keep a simple mapping: use Nunito for most UI, and selectively use Noto Naskh Arabic where Arabic text is rendered.
// You can override per-text with Modifier or style.fontFamily = NotoNaskhArabic as needed in UI code.
val Typography = Typography(
    titleLarge = Typography().titleLarge.copy(fontFamily = Nunito, fontWeight = FontWeight.SemiBold),
    titleMedium = Typography().titleMedium.copy(fontFamily = Nunito, fontWeight = FontWeight.SemiBold),
    bodyLarge = Typography().bodyLarge.copy(fontFamily = Nunito),
    bodyMedium = Typography().bodyMedium.copy(fontFamily = Nunito),
    labelLarge = Typography().labelLarge.copy(fontFamily = Nunito)
)
