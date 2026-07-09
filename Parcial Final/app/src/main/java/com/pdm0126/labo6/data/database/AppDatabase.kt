package com.pdm0126.labo6.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pdm0126.labo6.data.database.dao.OptionDao
import com.pdm0126.labo6.data.database.dao.QuestionDao
import com.pdm0126.labo6.data.model.Option
import com.pdm0126.labo6.data.model.Question

@Database(entities = [Question::class, Option::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
    abstract fun optionDao(): OptionDao
}
