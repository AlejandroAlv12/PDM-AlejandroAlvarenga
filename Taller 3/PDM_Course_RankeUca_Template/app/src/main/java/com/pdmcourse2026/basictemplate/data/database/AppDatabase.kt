package com.pdmcourse2026.basictemplate.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pdmcourse2026.basictemplate.data.database.entities.OptionEntity

@Database(entities = [OptionEntity::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {
    abstract fun optionDao(): OptionDao
}
