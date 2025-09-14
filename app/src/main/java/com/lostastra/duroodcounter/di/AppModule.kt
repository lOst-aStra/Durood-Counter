package com.lostastra.duroodcounter.di

import com.lostastra.duroodcounter.data.CounterRepository

object AppModule {
    // Minimal manual DI. Replace with real implementations in later stories.
    fun provideCounterRepository(): CounterRepository = InMemoryCounterRepository()
}

private class InMemoryCounterRepository : CounterRepository {
    private var count: Int = 0
    override suspend fun getCount(): Int = count
    override suspend fun setCount(value: Int) {
        count = value
    }
}
