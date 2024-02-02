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
        maven(url = "https://jitpack.io")
        maven(url = "https://jitpack.io")
    }
}

rootProject.name = "Fake Store"
include(":app")
include(":core")
include(":network")
include(":database")
include(":cart")
include(":authentication")
include(":products")
include(":user")
