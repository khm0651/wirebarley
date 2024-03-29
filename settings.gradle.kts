pluginManagement {
    includeBuild("buildLogic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "wirebarley"
include(":app")
include(":core:domain")
include(":core:model")
include(":core:test")
include(":feature:exchangeRate")
include(":core:data")
include(":core:dataSourceLocal:inMemory")
include(":core:dataSourceRemote:apilayer")
include(":core:dataSourceLocal:room")
include(":core:common")
