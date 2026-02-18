package com.base.navigation

sealed interface NavigationRoute {
    val route: String

    data object Login : NavigationRoute {
        override val route = "login"
    }

    data object Register : NavigationRoute {
        override val route = "register"
    }

    data object Home : NavigationRoute {
        override val route = "home"
    }

    data object Settings : NavigationRoute {
        override val route = "settings"
    }

    data object Favorites : NavigationRoute {
        override val route = "favorites"
    }
    
    // Helper to match string routes
    companion object {
        fun fromRoute(route: String?): NavigationRoute? {
            return when (route) {
                Login.route -> Login
                Register.route -> Register
                Home.route -> Home
                Settings.route -> Settings
                Favorites.route -> Favorites
                else -> null
            }
        }
    }
}
