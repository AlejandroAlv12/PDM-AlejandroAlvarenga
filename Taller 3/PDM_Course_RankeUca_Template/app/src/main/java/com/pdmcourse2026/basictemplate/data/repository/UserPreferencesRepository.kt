package com.pdmcourse2026.basictemplate.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface UserPreferencesRepository {
    val selectedPlaceId: Flow<Int?>
    val hasVoted: Flow<Boolean>
    suspend fun saveSelectedPlaceId(placeId: Int)
    suspend fun saveVotedState(voted: Boolean)
}

class UserPreferencesRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : UserPreferencesRepository {

    private object PreferencesKeys {
        val SELECTED_PLACE_ID = intPreferencesKey("selected_place_id")
        val HAS_VOTED = booleanPreferencesKey("has_voted")
    }

    override val selectedPlaceId: Flow<Int?> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.SELECTED_PLACE_ID]
        }

    override val hasVoted: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[PreferencesKeys.HAS_VOTED] ?: false
        }

    override suspend fun saveSelectedPlaceId(placeId: Int) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.SELECTED_PLACE_ID] = placeId
        }
    }

    override suspend fun saveVotedState(voted: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.HAS_VOTED] = voted
        }
    }
}
