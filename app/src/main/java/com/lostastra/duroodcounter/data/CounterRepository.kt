package com.lostastra.duroodcounter.data

interface CounterRepository {
    suspend fun getCount(): Int
    suspend fun setCount(value: Int)
}
