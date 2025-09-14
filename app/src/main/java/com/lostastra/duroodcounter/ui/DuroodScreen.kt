package com.lostastra.duroodcounter.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.lostastra.duroodcounter.presentation.CounterViewModel
import com.lostastra.duroodcounter.ui.components.CounterCard
import com.lostastra.duroodcounter.ui.components.HeaderArabic
import com.lostastra.duroodcounter.ui.components.PrimaryButton
import com.lostastra.duroodcounter.di.AppModule

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

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 24.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderArabic(
                modifier = Modifier.fillMaxWidth()
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CounterCard(
                    count = state.count
                )
            }

            PrimaryButton(
                onClick = { viewModel.onIncrement() }
            )
        }
    }
}
