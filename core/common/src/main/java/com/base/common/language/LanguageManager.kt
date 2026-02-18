package com.base.common.language

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

enum class Language(val code: String) {
    ENGLISH("en"),
    TURKISH("tr"),
    GERMAN("de"),
    SYSTEM("system")
}

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "language_settings")

@Singleton
class LanguageManager @Inject constructor(@ApplicationContext private val context: Context) {

    private val languageKey = stringPreferencesKey("language")

    val language = context.dataStore.data.map { preferences ->
        Language.valueOf(preferences[languageKey] ?: Language.SYSTEM.name)
    }

    suspend fun setLanguage(language: Language) {
        context.dataStore.edit { settings ->
            settings[languageKey] = language.name
        }
    }
}
