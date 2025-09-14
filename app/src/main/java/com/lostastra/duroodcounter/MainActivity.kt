package com.lostastra.duroodcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.lostastra.duroodcounter.ui.theme.DuroodCounterTheme
import com.lostastra.duroodcounter.ui.DuroodScreen
import com.lostastra.duroodcounter.ui.theme.md_background
import com.lostastra.duroodcounter.ui.theme.md_surface

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DuroodCounterTheme {
                AppScaffold()
            }
        }
    }
}

@Composable
private fun AppScaffold() {
    val gradient = Brush.verticalGradient(
        colors = listOf(
            md_background,
            md_surface
        )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color.Transparent
        ) { _ ->
            DuroodScreen()
        }
    }
}
