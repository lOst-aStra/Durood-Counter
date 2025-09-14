package com.lostastra.duroodcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.lostastra.duroodcounter.ui.theme.DuroodCounterTheme
import com.lostastra.duroodcounter.ui.DuroodScreen

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
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        DuroodScreen()
    }
}
