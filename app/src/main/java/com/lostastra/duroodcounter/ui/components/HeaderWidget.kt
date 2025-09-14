package com.lostastra.duroodcounter.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Nightlight
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lostastra.duroodcounter.R
import com.lostastra.duroodcounter.ui.theme.ArabicFontFamily
import com.lostastra.duroodcounter.ui.theme.LatinRoundedFontFamily

@Composable
fun HeaderWidget(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title with icons
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Outlined.Nightlight,
                contentDescription = stringResource(id = R.string.cd_icon_crescent),
                modifier = Modifier.testTag("icon-crescent"),
                tint = MaterialTheme.colorScheme.primary
            )
            
            Text(
                text = stringResource(id = R.string.durood_shareef_title),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = LatinRoundedFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
                ),
                textAlign = TextAlign.Center
            )
            
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = stringResource(id = R.string.cd_icon_star),
                modifier = Modifier.testTag("icon-star"),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Durood text in a card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFFAFAFA)
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 2.dp
            )
        ) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                Text(
                    text = stringResource(id = R.string.durood_shareef_full),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .testTag("durood-text"),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = ArabicFontFamily,
                        fontSize = 24.sp,
                        color = Color(0xFF000000)
                    ),
                    textAlign = TextAlign.Center
                )
            }
        }

        // Arabic line directly below title
        HeaderArabic(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
        )
    }
}
