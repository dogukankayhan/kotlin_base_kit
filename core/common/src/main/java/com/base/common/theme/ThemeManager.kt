package com.base.common.theme

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

enum class Theme {
    LIGHT,
    DARK,
    SYSTEM
}

@Singleton
class ThemeManager @Inject constructor(private val dataStore: DataStore<Preferences>) {

    private val themeKey = stringPreferencesKey("theme")

    val theme = dataStore.data.map { preferences ->
        Theme.valueOf(preferences[themeKey] ?: Theme.SYSTEM.name)
    }

    suspend fun setTheme(theme: Theme) {
        dataStore.edit { settings ->
            settings[themeKey] = theme.name
        }
    }
}
