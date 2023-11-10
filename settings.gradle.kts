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

rootProject.name = "Hayate"
include(":app")
include(":feature:anime:exploration:ui")
include(":feature:anime:exploration:domain")
include(":feature:anime:exploration:data")
include(":feature:anime:collection:ui")
include(":feature:anime:collection:domain")
include(":feature:anime:collection:data")
include(":feature:settings:ui")
include(":feature:settings:domain")
include(":feature:settings:data")
include(":common:theme")
include(":common:preferences")
include(":feature:anime:shared")
include(":common:shared")
