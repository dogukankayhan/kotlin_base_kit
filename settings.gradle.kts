pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "KotlinBaseKit"
include(":app")
include(":core:network")
include(":core:domain")
include(":core:data")
include(":core:ui")
include(":core:auth")
include(":core:model")
include(":core:common")
include(":core:navigation")
include(":feature:auth")
include(":core:deeplink")
include(":feature:favorites")
include(":core:config")
