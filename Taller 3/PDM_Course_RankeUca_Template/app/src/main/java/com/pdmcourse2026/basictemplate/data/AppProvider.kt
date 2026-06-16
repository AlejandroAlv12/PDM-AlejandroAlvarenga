package com.pdmcourse2026.basictemplate.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.pdmcourse2026.basictemplate.data.database.AppDatabase
import com.pdmcourse2026.basictemplate.data.repository.OptionRepository
import com.pdmcourse2026.basictemplate.data.repository.OptionRepositoryImpl
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaRepository
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaRepositoryImpl
import com.pdmcourse2026.basictemplate.data.repository.UserPreferencesRepository
import com.pdmcourse2026.basictemplate.data.repository.UserPreferencesRepositoryImpl

private val Context.dataStore by preferencesDataStore(name = "user_preferences")

class AppProvider(context: Context) {
    private val database: AppDatabase by lazy {
        AppDatabase.getDatabase(context)
    }

    private val optionDao by lazy {
        database.optionDao()
    }

    val optionRepository: OptionRepository by lazy {
        OptionRepositoryImpl(optionDao)
    }

    val rankeUcaRepository: RankeUcaRepository by lazy {
        RankeUcaRepositoryImpl(dao = optionDao)
    }

    val userPreferencesRepository: UserPreferencesRepository by lazy {
        UserPreferencesRepositoryImpl(context.dataStore)
    }
}
