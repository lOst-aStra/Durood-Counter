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
    suspend fun incrementBy(amount: Int)
}

class DefaultCounterRepository(
    private val local: CounterLocalDataSource
) : CounterRepository {

    override val flow: Flow<CounterSnapshot> = local.data.map { dto ->
        CounterSnapshot(count = dto.currentCount, completedSets = dto.completedSets)
    }

    override suspend fun increment() {
        incrementBy(1)
    }

    override suspend fun decrement() {
        local.updateAtomic(timestampMs = System.currentTimeMillis()) { count, sets ->
            (count - 1).coerceAtLeast(0) to sets
        }
    }

    override suspend fun reset() {
        local.setValues(count = 0, completedSets = 0, timestampMs = System.currentTimeMillis())
    }

    override suspend fun incrementBy(amount: Int) {
        local.updateAtomic(timestampMs = System.currentTimeMillis()) { count, sets ->
            val newCount = count + amount
            if (newCount >= 100) {
                (newCount % 100) to (sets + newCount / 100)
            } else {
                newCount to sets
            }
        }
    }
}
