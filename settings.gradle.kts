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

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "Hayate"
include(":app")

include(":feature:anime:collection:ui")
include(":feature:anime:collection:domain")
include(":feature:anime:collection:data")
include(":feature:settings:ui")
include(":feature:settings:domain")
include(":feature:settings:data")
include(":common:theme")
include(":common:preferences")
include(":common:shared")

include(":feature:anime:detail:ui")

//=====Feature Anime=====
include(":feature:anime:core:common")
include(":feature:anime:core:data")
include(":feature:anime:core:domain")
include(":feature:anime:core:source:remote:api")
include(":feature:anime:core:source:remote:impl")
include(":feature:anime:core:source:local:api")
include(":feature:anime:core:source:local:impl")

//=====Anime - Explore=====
include(":feature:anime:exploration:domain")
include(":feature:anime:exploration:ui")
include(":feature:anime:initialization:ui")
include(":feature:anime:initialization:domain")
include(":common:firebase")
