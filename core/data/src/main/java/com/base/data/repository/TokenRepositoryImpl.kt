package com.base.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.base.domain.repository.TokenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : TokenRepository {

    private object PreferencesKeys {
        val ACCESS_TOKEN = stringPreferencesKey("access_token")
        val REFRESH_TOKEN = stringPreferencesKey("refresh_token")
    }

    override fun getAccessToken(): Flow<String?> {
        return dataStore.data.map {
            it[PreferencesKeys.ACCESS_TOKEN]
        }
    }

    override fun getRefreshToken(): Flow<String?> {
        return dataStore.data.map {
            it[PreferencesKeys.REFRESH_TOKEN]
        }
    }

    override suspend fun saveAccessToken(token: String) {
        dataStore.edit {
            it[PreferencesKeys.ACCESS_TOKEN] = token
        }
    }

    override suspend fun saveRefreshToken(token: String) {
        dataStore.edit {
            it[PreferencesKeys.REFRESH_TOKEN] = token
        }
    }

    override suspend fun clearTokens() {
        dataStore.edit {
            it.clear()
        }
    }
}
