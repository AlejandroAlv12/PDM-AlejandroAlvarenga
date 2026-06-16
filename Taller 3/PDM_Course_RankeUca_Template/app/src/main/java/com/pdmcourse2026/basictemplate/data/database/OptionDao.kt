package com.pdmcourse2026.basictemplate.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdmcourse2026.basictemplate.data.database.entities.OptionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface OptionDao {
    @Query("SELECT * FROM options")
    fun getAllOptions(): Flow<List<OptionEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(options: List<OptionEntity>)

    @Query("DELETE FROM options")
    suspend fun clearAll()
}
