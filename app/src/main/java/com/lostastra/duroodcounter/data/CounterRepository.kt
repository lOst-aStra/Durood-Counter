package com.lostastra.duroodcounter.data

import com.lostastra.duroodcounter.data.datastore.CounterLocalDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

data class CounterSnapshot(
    val count: Int,
    val completedSets: Int
)

interface CounterRepository {
    val flow: Flow<CounterSnapshot>
    suspend fun increment()
    suspend fun decrement()
    suspend fun reset()
}

class DefaultCounterRepository(
    private val local: CounterLocalDataSource
) : CounterRepository {

    override val flow: Flow<CounterSnapshot> = local.data.map { dto ->
        CounterSnapshot(count = dto.currentCount, completedSets = dto.completedSets)
    }

    override suspend fun increment() {
        local.updateAtomic(timestampMs = System.currentTimeMillis()) { count, sets ->
            if (count >= 99) 0 to (sets + 1) else (count + 1) to sets
        }
    }

    override suspend fun decrement() {
        local.updateAtomic(timestampMs = System.currentTimeMillis()) { count, sets ->
            when {
                count > 0 -> (count - 1) to sets
                count == 0 && sets > 0 -> 99 to (sets - 1)
                else -> 0 to 0
            }
        }
    }

    override suspend fun reset() {
        local.setValues(count = 0, completedSets = 0, timestampMs = System.currentTimeMillis())
    }
}
