package com.base.navigation

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

interface Navigator {
    val navigationCommands: Flow<NavigationCommand>
    
    suspend fun navigate(route: NavigationRoute)
    suspend fun popBackStack()
    suspend fun navigateAndClear(
        route: NavigationRoute, 
        popUpTo: NavigationRoute, 
        inclusive: Boolean = false
    )
}

sealed interface NavigationCommand {
    data class Navigate(val route: NavigationRoute) : NavigationCommand
    data object PopBackStack : NavigationCommand
    data class NavigateAndClear(
        val route: NavigationRoute, 
        val popUpTo: NavigationRoute, 
        val inclusive: Boolean
    ) : NavigationCommand
}

@Singleton
class AppNavigator @Inject constructor() : Navigator {

    private val _navigationCommands = Channel<NavigationCommand>(Channel.BUFFERED)
    override val navigationCommands: Flow<NavigationCommand> = _navigationCommands.receiveAsFlow()

    override suspend fun navigate(route: NavigationRoute) {
        _navigationCommands.send(NavigationCommand.Navigate(route))
    }

    override suspend fun popBackStack() {
        _navigationCommands.send(NavigationCommand.PopBackStack)
    }

    override suspend fun navigateAndClear(
        route: NavigationRoute,
        popUpTo: NavigationRoute,
        inclusive: Boolean
    ) {
        _navigationCommands.send(NavigationCommand.NavigateAndClear(route, popUpTo, inclusive))
    }
}
