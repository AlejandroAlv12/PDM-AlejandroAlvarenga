package com.pdm0126.labo6.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.pdm0126.labo6.data.model.Question
import com.pdm0126.labo6.data.model.QuestionWithOptions
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(questions: List<Question>)

    @Transaction
    @Query("SELECT * FROM questions")
    fun getQuestionsWithOptions(): Flow<List<QuestionWithOptions>>
    
    @Query("DELETE FROM questions")
    suspend fun deleteAll()
}
