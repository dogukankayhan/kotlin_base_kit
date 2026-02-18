package com.base.feature.auth.coordinator

import com.base.navigation.NavigationRoute
import com.base.navigation.Navigator
import javax.inject.Inject

interface AuthCoordinator {
    suspend fun navigateToRegister()
    suspend fun navigateToLogin()
    suspend fun navigateToHome()
}

class DefaultAuthCoordinator @Inject constructor(
    private val navigator: Navigator
) : AuthCoordinator {

    override suspend fun navigateToRegister() {
        navigator.navigate(NavigationRoute.Register)
    }

    override suspend fun navigateToLogin() {
        navigator.navigate(NavigationRoute.Login)
    }

    override suspend fun navigateToHome() {
        navigator.navigateAndClear(NavigationRoute.Home, NavigationRoute.Login, true)
    }
}
