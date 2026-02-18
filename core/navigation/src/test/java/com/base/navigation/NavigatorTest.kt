package com.base.navigation

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class NavigatorTest {

    private val navigator = AppNavigator()

    @Test
    fun `navigate emits Navigate command`() = runTest {
        navigator.navigate(NavigationRoute.Home)
        
        val command = navigator.navigationCommands.first()
        assertTrue(command is NavigationCommand.Navigate)
        assertEquals(NavigationRoute.Home, (command as NavigationCommand.Navigate).route)
    }

    @Test
    fun `popBackStack emits PopBackStack command`() = runTest {
        navigator.popBackStack()

        val command = navigator.navigationCommands.first()
        assertTrue(command is NavigationCommand.PopBackStack)
    }

    @Test
    fun `navigateAndClear emits NavigateAndClear command`() = runTest {
        navigator.navigateAndClear(NavigationRoute.Home, NavigationRoute.Login, true)

        val command = navigator.navigationCommands.first()
        assertTrue(command is NavigationCommand.NavigateAndClear)
        val navCommand = command as NavigationCommand.NavigateAndClear
        assertEquals(NavigationRoute.Home, navCommand.route)
        assertEquals(NavigationRoute.Login, navCommand.popUpTo)
        assertTrue(navCommand.inclusive)
    }
}
