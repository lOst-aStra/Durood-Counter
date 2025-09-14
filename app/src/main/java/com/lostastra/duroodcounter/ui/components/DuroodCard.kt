package com.lostastra.duroodcounter.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.shadow
import com.lostastra.duroodcounter.R

@Composable
fun DuroodCard(
    modifier: Modifier = Modifier
) {
    // Card as per spec: rounded 20dp, off-white background, subtle shadow, 20dp padding
    Card(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 2.dp,
                shape = RoundedCornerShape(20.dp),
                ambientColor = Color(0xFFE0E0E0),
                spotColor = Color(0xFFE0E0E0)
            ),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFAFAFA)
        )
    ) {
        // Text: Only Arabic Durood, default app font, size 24sp, black, centered, RTL
        CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
            Text(
                text = stringResource(id = R.string.durood_shareef_full),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .testTag("durood-card-text"),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 24.sp,
                    color = Color(0xFF000000)
                ),
                textAlign = TextAlign.Center,
                maxLines = 5
            )
        }
    }
}
