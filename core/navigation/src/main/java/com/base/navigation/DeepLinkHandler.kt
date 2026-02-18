package com.base.navigation

import android.net.Uri

object DeepLinkHandler {
    fun handleDeepLink(uri: Uri): NavigationRoute? {
        // Example: myapp://open/login -> path segments: [open, login]
        // Example: https://www.example.com/favorites -> path segments: [favorites]
        
        val pathSegments = uri.pathSegments
        if (pathSegments.isEmpty()) return null

        // Simple strategy: use the last segment as the route key
        // You might want more sophisticated matching based on host/scheme
        val routeKey = pathSegments.lastOrNull()

        return NavigationRoute.fromRoute(routeKey)
    }
}
