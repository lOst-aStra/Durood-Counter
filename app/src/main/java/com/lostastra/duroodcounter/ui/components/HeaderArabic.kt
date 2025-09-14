package com.lostastra.duroodcounter.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.platform.LocalLayoutDirection
import com.lostastra.duroodcounter.R
import com.lostastra.duroodcounter.ui.theme.ArabicFontFamily

@Composable
fun HeaderArabic(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.durood_shareef_full)
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Text(
            text = text,
            modifier = modifier
                .padding(vertical = 8.dp)
                .testTag("header-arabic"),
            style = MaterialTheme.typography.titleLarge.copy(
                fontFamily = ArabicFontFamily,
                fontSize = 24.sp
            ),
            textAlign = TextAlign.Center,
            maxLines = 3
        )
    }
}
