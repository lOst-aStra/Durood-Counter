package com.lostastra.duroodcounter.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lostastra.duroodcounter.data.CounterRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class CounterViewModel(
    private val repository: CounterRepository
) : ViewModel() {
    private val _state = MutableStateFlow(CounterState())
    val state: StateFlow<CounterState> = _state

    private var collectJob: Job? = null

    init {
        // Initialize from latest snapshot to avoid flicker, then keep collecting updates.
        collectJob = viewModelScope.launch {
            val initial = repository.flow.first()
            _state.value = CounterState(
                count = initial.count,
                completedSets = initial.completedSets
            )
            repository.flow.collectLatest { snapshot ->
                _state.value = CounterState(
                    count = snapshot.count,
                    completedSets = snapshot.completedSets
                )
            }
        }
    }

    // Intents
    fun onIncrement() {
        viewModelScope.launch { repository.increment() }
    }

    fun onIncrementBy(amount: Int) {
        viewModelScope.launch { repository.incrementBy(amount) }
    }

    fun onBulkPlus10() = onIncrementBy(10)
    fun onBulkPlus33() = onIncrementBy(33)

    fun onDecrement() {
        viewModelScope.launch { repository.decrement() }
    }

    fun onReset() {
        viewModelScope.launch { repository.reset() }
    }
}
