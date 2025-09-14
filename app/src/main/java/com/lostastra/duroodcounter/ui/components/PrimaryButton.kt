package com.lostastra.duroodcounter.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    hapticsEnabled: Boolean = false,
    onHaptic: (() -> Unit)? = null
) {
    Button(
        onClick = {
            if (hapticsEnabled) onHaptic?.invoke()
            onClick()
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .testTag("primary-plus1"),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text = "+1",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
