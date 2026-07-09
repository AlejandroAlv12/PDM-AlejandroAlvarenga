package com.pdm0126.labo6.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdm0126.labo6.data.model.Option

@Dao
interface OptionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(options: List<Option>)
    
    @Query("UPDATE options SET votes = :votes WHERE id = :optionId AND questionId = :questionId")
    suspend fun updateVotes(optionId: Int, questionId: Int, votes: Int)

    @Query("DELETE FROM options")
    suspend fun deleteAll()
}
