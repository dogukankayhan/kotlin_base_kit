package com.base.common.language

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

enum class Language(val code: String) {
    ENGLISH("en"),
    TURKISH("tr"),
    GERMAN("de"),
    SYSTEM("system")
}

@Singleton
class LanguageManager @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val languageKey = stringPreferencesKey("language")

    val language = dataStore.data.map { preferences ->
        Language.valueOf(preferences[languageKey] ?: Language.SYSTEM.name)
    }

    suspend fun setLanguage(language: Language) {
        dataStore.edit { settings ->
            settings[languageKey] = language.name
        }
    }
}
