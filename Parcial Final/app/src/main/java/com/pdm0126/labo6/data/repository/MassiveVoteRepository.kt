package com.pdm0126.labo6.data.repository

import com.pdm0126.labo6.data.model.QuestionWithOptions
import kotlinx.coroutines.flow.Flow

interface MassiveVoteRepository {
    fun getQuestionsWithOptions(): Flow<List<QuestionWithOptions>>
    suspend fun submitVotes(apiKey: String, votes: Map<Int, Int>)
}
