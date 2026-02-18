package com.example.kotlinbasekit.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.base.common.language.Language
import com.base.common.language.LanguageViewModel
import com.base.common.theme.Theme
import com.base.common.theme.ThemeViewModel
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.kotlinbasekit.R
import java.util.Locale

@Composable
fun SettingsScreen(
    themeViewModel: ThemeViewModel = hiltViewModel(),
    languageViewModel: LanguageViewModel = hiltViewModel()
) {
    val currentTheme by themeViewModel.theme.collectAsState()
    val currentLanguage by languageViewModel.language.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = stringResource(id = R.string.greeting_hello))
            Text(text = stringResource(id = R.string.greeting_good_morning))
            Text(text = stringResource(id = R.string.greeting_good_evening))
        }

        // Theme selection
        ThemeRadioButton(
            text = stringResource(id = R.string.theme_light),
            selected = currentTheme == Theme.LIGHT
        ) {
            themeViewModel.setTheme(Theme.LIGHT)
        }
        ThemeRadioButton(
            text = stringResource(id = R.string.theme_dark),
            selected = currentTheme == Theme.DARK
        ) {
            themeViewModel.setTheme(Theme.DARK)
        }
        ThemeRadioButton(
            text = stringResource(id = R.string.theme_system),
            selected = currentTheme == Theme.SYSTEM
        ) {
            themeViewModel.setTheme(Theme.SYSTEM)
        }

        // Language selection
        LanguageRadioButton(
            text = stringResource(id = R.string.language_english),
            selected = currentLanguage == Language.ENGLISH
        ) {
            languageViewModel.setLanguage(Language.ENGLISH)
            applyLanguage("en")
        }
        LanguageRadioButton(
            text = stringResource(id = R.string.language_turkish),
            selected = currentLanguage == Language.TURKISH
        ) {
            languageViewModel.setLanguage(Language.TURKISH)
            applyLanguage("tr")
        }
        LanguageRadioButton(
            text = stringResource(id = R.string.language_german),
            selected = currentLanguage == Language.GERMAN
        ) {
            languageViewModel.setLanguage(Language.GERMAN)
            applyLanguage("de")
        }
        LanguageRadioButton(
            text = stringResource(id = R.string.language_system),
            selected = currentLanguage == Language.SYSTEM
        ) {
            languageViewModel.setLanguage(Language.SYSTEM)
            applyLanguage(null)
        }
    }
}

private fun applyLanguage(languageCode: String?) {
    val localeList = if (languageCode != null) {
        LocaleListCompat.create(Locale(languageCode))
    } else {
        LocaleListCompat.getEmptyLocaleList()
    }
    AppCompatDelegate.setApplicationLocales(localeList)
}

@Composable
private fun ThemeRadioButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(text = text, modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
private fun LanguageRadioButton(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(text = text, modifier = Modifier.padding(start = 8.dp))
    }
}
