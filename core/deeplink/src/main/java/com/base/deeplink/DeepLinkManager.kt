package com.base.deeplink

import android.content.Intent
import android.net.Uri
import com.base.navigation.DeepLinkHandler
import com.base.navigation.NavigationRoute
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeepLinkManager @Inject constructor() {

    private val _deepLinkEvents = MutableSharedFlow<NavigationRoute>(replay = 0)
    val deepLinkEvents: SharedFlow<NavigationRoute> = _deepLinkEvents.asSharedFlow()

    fun handleIntent(intent: Intent?) {
        intent?.data?.let { uri ->
            DeepLinkHandler.handleDeepLink(uri)?.let { route ->
                _deepLinkEvents.tryEmit(route)
            }
        }
    }
    
    fun manuallyTrigger(uriString: String) {
        Uri.parse(uriString)?.let { uri ->
            DeepLinkHandler.handleDeepLink(uri)?.let { route ->
                _deepLinkEvents.tryEmit(route)
            }
        }
    }
}
