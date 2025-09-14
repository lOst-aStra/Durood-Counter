package com.lostastra.duroodcounter.data.datastore

import android.content.Context

// Placeholder for DataStore-based implementation to be completed in a later story.
class CounterLocalDataSource(private val context: Context) {
    suspend fun getCount(): Int = 0
    suspend fun setCount(value: Int) { /* no-op for now */ }
}
