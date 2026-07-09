package com.pdm0126.labo6.data.repository

import com.pdm0126.labo6.data.api.KtorClient
import com.pdm0126.labo6.data.api.dto.QuestionDto
import com.pdm0126.labo6.data.api.dto.VoteItemDto
import com.pdm0126.labo6.data.api.dto.VoteRequestDto
import com.pdm0126.labo6.data.api.dto.VoteResponseDto
import com.pdm0126.labo6.data.database.dao.OptionDao
import com.pdm0126.labo6.data.database.dao.QuestionDao
import com.pdm0126.labo6.data.model.Option
import com.pdm0126.labo6.data.model.Question
import com.pdm0126.labo6.data.model.QuestionWithOptions
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import kotlinx.coroutines.flow.Flow

class KtorMassiveVoteRepositoryImpl(
    private val questionDao: QuestionDao,
    private val optionDao: OptionDao
) : MassiveVoteRepository {

    private val baseUrl = "https://qjcxdvfzyseuvezacxsd.supabase.co/functions/v1/rankeuca/parcialtres"

    override fun getQuestionsWithOptions(): Flow<List<QuestionWithOptions>> {
        return questionDao.getQuestionsWithOptions()
    }



    override suspend fun submitVotes(apiKey: String, votes: Map<Int, Int>) {
        val voteItems = votes.map { VoteItemDto(questionId = it.key, optionId = it.value) }
        val requestDto = VoteRequestDto(votes = voteItems)

        val response: HttpResponse = KtorClient.client.post("$baseUrl/votes") {
            header("Authorization", "Bearer $apiKey")
            contentType(ContentType.Application.Json)
            setBody(requestDto)
        }
        
        val responseText = response.bodyAsText()
        if (!response.status.isSuccess() || responseText.contains("\"ok\":false")) {
            throw Exception(responseText)
        }

        val json = kotlinx.serialization.json.Json { ignoreUnknownKeys = true }
        val voteResponse = json.decodeFromString<VoteResponseDto>(responseText)


        voteResponse.updated.forEach { updatedOption ->
            optionDao.updateVotes(
                optionId = updatedOption.id,
                questionId = updatedOption.questionId,
                votes = updatedOption.votes
            )
        }
    }
}
