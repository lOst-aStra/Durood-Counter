package com.lostastra.duroodcounter.di

import android.content.Context
import com.lostastra.duroodcounter.data.CounterRepository
import com.lostastra.duroodcounter.data.DefaultCounterRepository
import com.lostastra.duroodcounter.data.datastore.CounterLocalDataSource

object AppModule {
    // Manual DI providers
    fun provideCounterRepository(context: Context): CounterRepository =
        DefaultCounterRepository(
            local = CounterLocalDataSource(context.applicationContext)
        )
}
