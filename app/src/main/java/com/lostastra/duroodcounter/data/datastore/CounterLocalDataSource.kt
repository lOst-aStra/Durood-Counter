package com.lostastra.duroodcounter.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Preferences DataStore holder on Context
private val Context.counterDataStore: DataStore<Preferences> by preferencesDataStore(
    name = "counter_prefs.pb"
)

data class CounterPrefsDto(
    val currentCount: Int,
    val completedSets: Int,
    val lastUpdatedEpochMs: Long?
)

class CounterLocalDataSource(private val context: Context) {

    private object Keys {
        val CURRENT_COUNT = intPreferencesKey("current_count")
        val COMPLETED_SETS = intPreferencesKey("completed_sets")
        val LAST_UPDATED = longPreferencesKey("last_updated_epoch_ms")
    }

    // Expose a Flow mapping preferences to a small DTO
    val data: Flow<CounterPrefsDto> = context.counterDataStore.data.map { prefs ->
        CounterPrefsDto(
            currentCount = prefs[Keys.CURRENT_COUNT] ?: 0,
            completedSets = prefs[Keys.COMPLETED_SETS] ?: 0,
            lastUpdatedEpochMs = prefs[Keys.LAST_UPDATED]
        )
    }

    // Atomic setters using update (edit) which is transactional
    suspend fun setCurrentCount(value: Int, timestampMs: Long? = null) {
        context.counterDataStore.edit { prefs ->
            prefs[Keys.CURRENT_COUNT] = value
            timestampMs?.let { prefs[Keys.LAST_UPDATED] = it }
        }
    }

    suspend fun setCompletedSets(value: Int, timestampMs: Long? = null) {
        context.counterDataStore.edit { prefs ->
            prefs[Keys.COMPLETED_SETS] = value
            timestampMs?.let { prefs[Keys.LAST_UPDATED] = it }
        }
    }

    // Combined atomic update for both fields
    suspend fun setValues(count: Int, completedSets: Int, timestampMs: Long? = null) {
        context.counterDataStore.edit { prefs ->
            prefs[Keys.CURRENT_COUNT] = count
            prefs[Keys.COMPLETED_SETS] = completedSets
            timestampMs?.let { prefs[Keys.LAST_UPDATED] = it }
        }
    }

    // True atomic RMW (read-modify-write) using a single edit transaction
    suspend fun updateAtomic(
        timestampMs: Long? = null,
        transform: (currentCount: Int, completedSets: Int) -> Pair<Int, Int>
    ) {
        context.counterDataStore.edit { prefs ->
            val current = prefs[Keys.CURRENT_COUNT] ?: 0
            val sets = prefs[Keys.COMPLETED_SETS] ?: 0
            val (newCount, newSets) = transform(current, sets)
            prefs[Keys.CURRENT_COUNT] = newCount
            prefs[Keys.COMPLETED_SETS] = newSets
            timestampMs?.let { prefs[Keys.LAST_UPDATED] = it }
        }
    }
}
