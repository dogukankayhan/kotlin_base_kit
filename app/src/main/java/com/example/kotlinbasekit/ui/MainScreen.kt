package com.example.kotlinbasekit.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.base.feature.favorites.FavoritesScreen
import com.base.feature.movies.dashboard.DashboardScreen
import com.base.feature.movies.detail.MovieDetailScreen
import com.base.feature.movies.search.SearchScreen
import com.base.feature.movies.settings.SettingsScreen
import com.base.navigation.Arg
import com.base.navigation.Route
import com.example.kotlinbasekit.ui.splash.SplashScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentDestination = navBackStackEntry?.destination

            val items = listOf(
                Route.DASHBOARD to Icons.Default.List,
                Route.SEARCH to Icons.Default.Search,
                Route.FAVORITES to Icons.Default.Favorite,
                Route.SETTINGS to Icons.Default.Settings
            )

            val showBottomBar = currentDestination?.route in items.map { it.first }

            if (showBottomBar) {
                NavigationBar {
                    items.forEach { (screen, icon) ->
                        NavigationBarItem(
                            icon = { Icon(icon, contentDescription = null) },
                            label = { Text(screen.replaceFirstChar { it.uppercase() }) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen } == true,
                            onClick = {
                                navController.navigate(screen) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.SPLASH,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Route.SPLASH) {
                SplashScreen(
                    onSplashFinished = {
                        navController.navigate(Route.DASHBOARD) {
                            popUpTo(Route.SPLASH) { inclusive = true }
                        }
                    }
                )
            }
            composable(Route.DASHBOARD) {
                DashboardScreen()
            }
            composable(Route.SEARCH) {
                SearchScreen()
            }
            composable(Route.FAVORITES) {
                FavoritesScreen(
                    onNavigateToDetail = { id ->
                        navController.navigate("${Route.MOVIE_DETAIL}/$id")
                    }
                )
            }
            composable(Route.SETTINGS) {
                SettingsScreen()
            }
            composable("${Route.MOVIE_DETAIL}/{${Arg.MOVIE_ID}}") { backStackEntry ->
                val movieId = backStackEntry.arguments?.getString(Arg.MOVIE_ID)?.toIntOrNull()
                MovieDetailScreen(
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}
