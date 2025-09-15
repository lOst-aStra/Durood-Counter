package com.lostastra.duroodcounter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import com.lostastra.duroodcounter.ui.components.HeaderWidget
import com.lostastra.duroodcounter.ui.components.ActionButton
import com.lostastra.duroodcounter.di.AppModule
import androidx.compose.ui.graphics.Color
import com.lostastra.duroodcounter.ui.components.StatCard
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.foundation.shape.RoundedCornerShape

@Composable
fun DuroodScreen(
    modifier: Modifier = Modifier,
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
    val showResetDialog = remember { mutableStateOf(false) }

    Scaffold(
        modifier = modifier,
        containerColor = Color.Transparent
    ) { innerPadding ->
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

            Spacer(modifier = Modifier.height(32.dp)) // Increased spacing after header
            
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
                    .padding(top = 16.dp) // Increased top padding for the first card
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
                    label = "Total Recitations",
                    value = totalTasbih,
                    backgroundColor = Color(0xFFEBF5FB),
                    textColor = Color(0xFF1B4F72),
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    label = "Total Tasbih",
                    value = totalRecitations,
                    backgroundColor = Color(0xFFF5EBF9),
                    textColor = Color(0xFF4A235A),
                    modifier = Modifier.weight(1f)
                )
            }

            // Controls row: -1 (left below Total Tasbih) and +1 (right below Total Recitations)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 80.dp, bottom = 24.dp), // Increased top padding and bottom margin
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActionButton(
                    label = "1",
                    onClick = { viewModel.onDecrement() },
                    enabled = state.count > 0,
                    hapticsEnabled = true,
                    onHaptic = { haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove) },
                    contentDesc = "minus one decrement",
                    testTag = "action-minus1",
                    backgroundColor = Color(0xFFFFF3B0), // Soft Yellow
                    contentColor = Color(0xFF1B4F72), // Dark blue-gray to match card text
                    width = 100.dp,
                    height = 70.dp,
                    shape = RoundedCornerShape(20.dp),
                    fontSizeSp = 24
                )
                ActionButton(
                    label = "1",
                    onClick = { viewModel.onIncrement() },
                    hapticsEnabled = true,
                    onHaptic = { haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove) },
                    contentDesc = "plus one increment",
                    testTag = "action-plus1",
                    backgroundColor = Color(0xFFB5EAD7), // Soft Green (matching Current Tasbih card)
                    contentColor = Color(0xFF1B4F72), // Dark blue-gray to match card text
                    width = 100.dp,
                    height = 70.dp,
                    shape = RoundedCornerShape(20.dp),
                    fontSizeSp = 24
                )
            }

            // Bulk controls row: +10 and +33
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp, bottom = 24.dp), // Increased vertical padding
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                    ActionButton(
                        label = "10",
                        onClick = { viewModel.onBulkPlus10() },
                        hapticsEnabled = true,
                        onHaptic = { haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove) },
                        contentDesc = "plus ten increment",
                        testTag = "action-plus10",
                        backgroundColor = Color(0xFFA7C7E7), // Soft Blue (matching Total Tasbih card)
                        contentColor = Color(0xFF1B4F72), // Dark blue-gray to match card text
                        width = 90.dp,
                        height = 60.dp,
                        shape = RoundedCornerShape(20.dp),
                        fontSizeSp = 20 // Slightly smaller for "10" and "33" buttons
                    )
                    ActionButton(
                        label = "33",
                        onClick = { viewModel.onBulkPlus33() },
                        hapticsEnabled = true,
                        onHaptic = { haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove) },
                        contentDesc = "plus thirty three increment",
                        testTag = "action-plus33",
                        backgroundColor = Color(0xFFA7C7E7), // Matching +10 button color
                        contentColor = Color(0xFF1B4F72), // Dark blue-gray to match card text
                        width = 90.dp,
                        height = 60.dp,
                        shape = RoundedCornerShape(20.dp),
                        fontSizeSp = 20 // Slightly smaller for "10" and "33" buttons
                    )
                }
            }

            Spacer(modifier = Modifier.weight(1f))
            
            // Reset button below all, centered
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ActionButton(
                    label = "Reset",
                    onClick = { showResetDialog.value = true },
                    hapticsEnabled = true,
                    onHaptic = { haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove) },
                    contentDesc = "reset counter",
                    testTag = "action-reset",
                    backgroundColor = Color(0xFFFFB6C1), // Soft Red
                    contentColor = Color(0xFF8B0000), // Darker red for better contrast
                    width = 120.dp,
                    height = 50.dp,
                    shape = RoundedCornerShape(25.dp),
                    fontSizeSp = 16 // Slightly larger for Reset button
                )
            }

            if (showResetDialog.value) {
                AlertDialog(
                    onDismissRequest = { showResetDialog.value = false },
                    title = { Text(text = "Confirm Reset") },
                    text = { Text(text = "Are you sure you want to reset the Current Tasbih count?") },
                    confirmButton = {
                        TextButton(onClick = {
                            viewModel.onReset()
                            showResetDialog.value = false
                        }) { Text(text = "Yes") }
                    },
                    dismissButton = {
                        TextButton(onClick = { showResetDialog.value = false }) { Text(text = "No") }
                    }
                )
            }
        }
    }
}

