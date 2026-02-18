package com.example.kotlinbasekit

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.LocaleListCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.base.common.language.Language
import com.base.common.language.LanguageViewModel
import com.base.common.theme.ThemeViewModel
import com.base.feature.auth.login.LoginScreen
import com.base.feature.auth.register.RegisterScreen
import com.example.kotlinbasekit.ui.settings.SettingsScreen
import com.example.kotlinbasekit.ui.theme.KotlinBaseKitTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @javax.inject.Inject
    lateinit var navigator: com.base.navigation.Navigator

    @javax.inject.Inject
    lateinit var deepLinkManager: com.base.deeplink.DeepLinkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LanguageCheck", "MainActivity onCreate. Current Locales: ${AppCompatDelegate.getApplicationLocales()}")
        
        deepLinkManager.handleIntent(intent)

        setContent {
            val themeViewModel: ThemeViewModel = hiltViewModel()
            val theme by themeViewModel.theme.collectAsState()

            KotlinBaseKitTheme(theme = theme) {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    
                    // Observe Navigation Commands
                    LaunchedEffect(Unit) {
                        navigator.navigationCommands.collect { command ->
                            when (command) {
                                is com.base.navigation.NavigationCommand.Navigate -> {
                                    navController.navigate(command.route.route)
                                }
                                is com.base.navigation.NavigationCommand.PopBackStack -> {
                                    navController.popBackStack()
                                }
                                is com.base.navigation.NavigationCommand.NavigateAndClear -> {
                                    navController.navigate(command.route.route) {
                                        popUpTo(command.popUpTo.route) {
                                            inclusive = command.inclusive
                                        }
                                    }
                                }
                            }
                        }
                    }

                    // Deep Link Handling
                    LaunchedEffect(Unit) {
                        deepLinkManager.deepLinkEvents.collect { route ->
                             navController.navigate(route.route)
                        }
                    }

                    NavHost(navController = navController, startDestination = "login") {
                        composable("login") {
                            LoginScreen()
                        }
                        composable("register") {
                            RegisterScreen()
                        }
                        composable("home") {
                            val viewModel: com.example.kotlinbasekit.ui.MainViewModel = 
                                androidx.lifecycle.viewmodel.compose.viewModel()
                            val state = viewModel.uiState.collectAsState()

                            LaunchedEffect(Unit) {
                                viewModel.uiEffect.collect { effect ->
                                    when (effect) {
                                        is com.example.kotlinbasekit.ui.MainEffect.ShowToast -> {
                                            Toast.makeText(this@MainActivity, effect.message, Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                }
                            }

                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                if (state.value.isLoading) {
                                    CircularProgressIndicator()
                                } else {
                                    Text(text = state.value.data ?: "No Data")
                                    Button(
                                        onClick = { 
                                            viewModel.setEvent(com.example.kotlinbasekit.ui.MainEvent.LoadDat) 
                                        }
                                    ) {
                                        Text("Load Data")
                                    }
                                    Button(onClick = { navController.navigate("settings") }) {
                                        Text("Settings")
                                    }
                                    Button(onClick = { navController.navigate("favorites") }) {
                                        Text("Favorites")
                                    }
                                }
                            }
                        }
                        composable("settings") {
                            SettingsScreen()
                        }
                        composable("favorites") {
                            com.base.feature.favorites.FavoritesScreen()
                        }
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: android.content.Intent?) {
        super.onNewIntent(intent)
        deepLinkManager.handleIntent(intent)
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val themeViewModel: ThemeViewModel = hiltViewModel()
    val theme by themeViewModel.theme.collectAsState()
    KotlinBaseKitTheme(theme = theme) {
        Greeting("Android")
    }
}
