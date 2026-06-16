package com.pdmcourse2026.basictemplate.data.repository

import com.pdmcourse2026.basictemplate.data.api.RankeUcaService
import com.pdmcourse2026.basictemplate.data.api.dto.OptionDto
import com.pdmcourse2026.basictemplate.ui.models.PlaceModel

interface RankeUcaRepository {
    suspend fun getPlaces(): Result<List<PlaceModel>>
    suspend fun voteForPlace(optionId: Int): Result<Boolean>
}

class RankeUcaRepositoryImpl(
    private val service: RankeUcaService = RankeUcaService()
) : RankeUcaRepository {

    override suspend fun getPlaces(): Result<List<PlaceModel>> {
        return try {
            val options = service.getOptions()
            Result.success(options.map { it.toModel() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun voteForPlace(optionId: Int): Result<Boolean> {
        return try {
            val response = service.vote(optionId)
            if (response.ok) {
                Result.success(true)
            } else {
                Result.failure(Exception(response.message ?: "Error desconocido"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun OptionDto.toModel() = PlaceModel(
        id = id,
        name = name,
        imageUrl = imageUrl,
        votes = votes
    )
}
