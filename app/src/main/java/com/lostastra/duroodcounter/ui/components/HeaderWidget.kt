package com.lostastra.duroodcounter.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Nightlight
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lostastra.duroodcounter.R
import com.lostastra.duroodcounter.ui.theme.LatinRoundedFontFamily

@Composable
fun HeaderWidget(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title row with icons flanking the centered title
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .height(40.dp),
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
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 22.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
                    .testTag("header-title"),
                textAlign = TextAlign.Center,
                maxLines = 1
            )

            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = stringResource(id = R.string.cd_icon_star),
                modifier = Modifier.testTag("icon-star"),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        // Arabic line directly below title
        HeaderArabic(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
        )
    }
}
