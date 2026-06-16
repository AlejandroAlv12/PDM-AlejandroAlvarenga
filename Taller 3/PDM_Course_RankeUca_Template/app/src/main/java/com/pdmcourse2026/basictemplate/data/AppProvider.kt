package com.pdmcourse2026.basictemplate.data

import android.content.Context
import com.pdmcourse2026.basictemplate.data.database.AppDatabase
import com.pdmcourse2026.basictemplate.data.repository.OptionRepository
import com.pdmcourse2026.basictemplate.data.repository.OptionRepositoryImpl

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
}
