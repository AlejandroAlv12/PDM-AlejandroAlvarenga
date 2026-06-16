package com.pdmcourse2026.basictemplate

import android.app.Application
import androidx.room.Room
import com.pdmcourse2026.basictemplate.data.database.AppDatabase
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaRepository
import com.pdmcourse2026.basictemplate.data.repository.RankeUcaRepositoryImpl

class RankeUCAApplication : Application() {
    private val database by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "rankeuca_database"
        ).build()
    }

    val repository: RankeUcaRepository by lazy {
        RankeUcaRepositoryImpl(dao = database.optionDao())
    }
}
