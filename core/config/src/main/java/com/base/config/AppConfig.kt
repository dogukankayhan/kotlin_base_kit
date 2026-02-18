package com.base.config

import javax.inject.Inject
import javax.inject.Singleton

interface AppConfig {
    val environment: Environment
    val baseUrl: String
    val isDebug: Boolean
}

@Singleton
class AppConfigImpl @Inject constructor() : AppConfig {
    
    // In a real app, these values would come from BuildConfig or a hidden file.
    // We can also have flavors inject different implementations or values.
    
    override val environment: Environment = Environment.DEV // Default for now

    override val baseUrl: String
        get() = when (environment) {
            Environment.DEV -> "https://reqres.in/api/"
            Environment.STAGING -> "https://staging.reqres.in/api/"
            Environment.PROD -> "https://reqres.in/api/"
        }

    override val isDebug: Boolean = true
}
