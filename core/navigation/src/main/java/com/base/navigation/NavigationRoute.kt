package com.base.navigation

sealed interface NavigationRoute {
    val route: String

    data object Login : NavigationRoute {
        override val route = "login"
    }

    data object Register : NavigationRoute {
        override val route = "register"
    }

    data object Dashboard : NavigationRoute {
        override val route = "dashboard"
    }

    data object Search : NavigationRoute {
        override val route = "search"
    }

    data object Settings : NavigationRoute {
        override val route = "settings"
    }

    data object Favorites : NavigationRoute {
        override val route = "favorites"
    }
    
    data object MovieDetail : NavigationRoute {
        override val route = "movie_detail/{movieId}"
        fun createRoute(movieId: Int) = "movie_detail/$movieId"
    }

    // Helper to match string routes
    companion object {
        fun fromRoute(route: String?): NavigationRoute? {
            if (route == null) return null
            if (route.startsWith("movie_detail/")) return MovieDetail
            return when (route) {
                Login.route -> Login
                Register.route -> Register
                Dashboard.route -> Dashboard
                Search.route -> Search
                Settings.route -> Settings
                Favorites.route -> Favorites
                else -> null
            }
        }
    }
}
