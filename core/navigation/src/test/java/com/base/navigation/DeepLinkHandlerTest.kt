package com.base.navigation

import android.net.Uri
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class DeepLinkHandlerTest {

    private val deepLinkHandler = DeepLinkHandler

    @Test
    fun `handleDeepLink returns null when pathSegments is empty`() {
        val uri = mockk<Uri> {
            every { pathSegments } returns emptyList()
        }
        val result = deepLinkHandler.handleDeepLink(uri)
        assertNull(result)
    }

    @Test
    fun `handleDeepLink returns Login for login path`() {
        val uri = mockk<Uri> {
            every { pathSegments } returns listOf("login")
        }
        val result = deepLinkHandler.handleDeepLink(uri)
        assertEquals(NavigationRoute.Login, result)
    }

    @Test
    fun `handleDeepLink returns Register for register path`() {
        val uri = mockk<Uri> {
            every { pathSegments } returns listOf("register")
        }
        val result = deepLinkHandler.handleDeepLink(uri)
        assertEquals(NavigationRoute.Register, result)
    }

    @Test
    fun `handleDeepLink returns null for unknown path`() {
        val uri = mockk<Uri> {
            every { pathSegments } returns listOf("unknown")
        }
        val result = deepLinkHandler.handleDeepLink(uri)
        assertNull(result)
    }
}
