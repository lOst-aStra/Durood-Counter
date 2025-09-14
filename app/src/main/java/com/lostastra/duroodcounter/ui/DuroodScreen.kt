package com.lostastra.duroodcounter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import com.lostastra.duroodcounter.presentation.CounterViewModel
import com.lostastra.duroodcounter.ui.components.CounterCard
import com.lostastra.duroodcounter.ui.components.HeaderWidget
import com.lostastra.duroodcounter.ui.components.PrimaryButton
import com.lostastra.duroodcounter.ui.components.ActionButton
import com.lostastra.duroodcounter.di.AppModule
import androidx.compose.ui.graphics.Color
import com.lostastra.duroodcounter.ui.components.StatCard

@Composable
fun DuroodScreen(
    viewModel: CounterViewModel = run {
        val context = LocalContext.current
        viewModel(factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(CounterViewModel::class.java)) {
                    val repo = AppModule.provideCounterRepository(context)
                    return CounterViewModel(repo) as T
                }
                throw IllegalArgumentException("Unknown ViewModel class")
            }
        })
    }
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val haptic = LocalHapticFeedback.current

    Scaffold(containerColor = Color.Transparent) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header with Durood text in card and icons
            HeaderWidget(
                modifier = Modifier.fillMaxWidth()
            )

            // Progress tracking cards (Story 2)
            val currentTasbih = state.count
            val totalTasbih = state.completedSets * 100 + state.count
            val totalRecitations = state.completedSets

            // Current Tasbih full-width card
            StatCard(
                label = "Current Tasbih",
                value = currentTasbih,
                backgroundColor = Color(0xFFE8F8F5),
                textColor = Color(0xFF145A32),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            )

            // Row with Total Tasbih and Total Recitations
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatCard(
                    label = "Total Tasbih",
                    value = totalTasbih,
                    backgroundColor = Color(0xFFEBF5FB),
                    textColor = Color(0xFF1B4F72),
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    label = "Total Recitations",
                    value = totalRecitations,
                    backgroundColor = Color(0xFFF5EBF9),
                    textColor = Color(0xFF4A235A),
                    modifier = Modifier.weight(1f)
                )
            }

            // Spacer to push controls to bottom
            Spacer(modifier = Modifier.weight(1f))

            // Controls row: -1 and +1
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActionButton(
                    label = "-1",
                    onClick = { viewModel.onDecrement() },
                    enabled = state.count > 0,
                    hapticsEnabled = true,
                    onHaptic = { haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove) },
                    contentDesc = "minus one decrement",
                    testTag = "action-minus1"
                )
                PrimaryButton(
                    onClick = { viewModel.onIncrement() },
                    hapticsEnabled = true,
                    onHaptic = { haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove) }
                )
            }

            // Bulk controls row: +10 and +33
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActionButton(
                    label = "+10",
                    onClick = { viewModel.onBulkPlus10() },
                    hapticsEnabled = true,
                    onHaptic = { haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove) },
                    contentDesc = "plus ten increment",
                    testTag = "action-plus10"
                )
                ActionButton(
                    label = "+33",
                    onClick = { viewModel.onBulkPlus33() },
                    hapticsEnabled = true,
                    onHaptic = { haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove) },
                    contentDesc = "plus thirty three increment",
                    testTag = "action-plus33"
                )
            }
        }
    }
}
