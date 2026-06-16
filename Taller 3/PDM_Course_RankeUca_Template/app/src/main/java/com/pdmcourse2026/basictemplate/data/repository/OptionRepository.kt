package com.pdmcourse2026.basictemplate.data.repository

import com.pdmcourse2026.basictemplate.data.database.dao.OptionDao
import com.pdmcourse2026.basictemplate.data.database.entities.toEntity
import com.pdmcourse2026.basictemplate.data.database.entities.toModel
import com.pdmcourse2026.basictemplate.data.model.Option
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface OptionRepository {
    fun getAllOptions(): Flow<List<Option>>
    suspend fun insertOption(option: Option)
    suspend fun deleteOption(option: Option)
}

class OptionRepositoryImpl(private val optionDao: OptionDao) : OptionRepository {
    override fun getAllOptions(): Flow<List<Option>> =
        optionDao.getAllOptions().map { entities ->
            entities.map { it.toModel() }
        }

    override suspend fun insertOption(option: Option) {
        optionDao.insertOption(option.toEntity())
    }

    override suspend fun deleteOption(option: Option) {
        optionDao.deleteOption(option.toEntity())
    }
}
