package com.pdmcourse2026.basictemplate.data.repository

import com.pdmcourse2026.basictemplate.data.api.RankeUcaService
import com.pdmcourse2026.basictemplate.data.api.dto.OptionDto
import com.pdmcourse2026.basictemplate.data.database.OptionDao
import com.pdmcourse2026.basictemplate.data.database.entities.OptionEntity
import com.pdmcourse2026.basictemplate.data.database.entities.toPlaceModel
import com.pdmcourse2026.basictemplate.ui.models.PlaceModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface RankeUcaRepository {
    fun getPlacesFlow(): Flow<List<PlaceModel>>
    suspend fun fetchPlaces(): Result<Unit>
    suspend fun voteForPlace(optionId: Int): Result<Boolean>
}

class RankeUcaRepositoryImpl(
    private val service: RankeUcaService = RankeUcaService(),
    private val dao: OptionDao
) : RankeUcaRepository {

    override fun getPlacesFlow(): Flow<List<PlaceModel>> {
        return dao.getAllOptions().map { entities ->
            entities.map { it.toPlaceModel() }
        }
    }

    override suspend fun fetchPlaces(): Result<Unit> {
        return try {
            val options = service.getOptions()
            dao.clearAll()
            dao.insertAll(options.map { it.toEntity() })
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun voteForPlace(optionId: Int): Result<Boolean> {
        return try {
            val response = service.vote(optionId)
            if (response.ok) {
                // Refresh data after voting to get updated counts
                fetchPlaces()
                Result.success(true)
            } else {
                Result.failure(Exception(response.message ?: "Error desconocido"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun OptionDto.toEntity() = OptionEntity(
        id = id,
        name = name,
        imageUrl = imageUrl,
        votes = votes
    )
}
