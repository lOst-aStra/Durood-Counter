package com.lostastra.duroodcounter.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lostastra.duroodcounter.ui.theme.LatinRoundedFontFamily

@Composable
fun CounterCard(
    count: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Text(
            text = count.toString(),
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 16.dp)
                .testTag("counter-value"),
            style = MaterialTheme.typography.displaySmall.copy(
                fontFamily = LatinRoundedFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 48.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}
