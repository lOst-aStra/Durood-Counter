package com.lostastra.duroodcounter.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CounterViewModel : ViewModel() {
    private val _state = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = _state

    fun increment() {
        _state.value = _state.value.copy(count = _state.value.count + 1)
    }

    fun reset() {
        _state.value = CounterState()
    }
}
