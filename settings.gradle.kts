pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "LizaNote"
include(
    ":app",
    ":flows",
    ":core:viewmodel-event:annotations",
    ":core:viewmodel-event:processor",
    ":core:viewmodel",
    ":core:navigation:api",
    ":core:navigation:impl",
    ":core:navigation:utils",
    ":core:test",
    ":core:uikit",

    ":feature:notes:ui:api",
    ":feature:notes:ui:impl",
    ":feature:notes:domain:impl",
    ":feature:notes:domain:api",
    ":feature:notes:flow",

    ":feature:note:flow",
    ":feature:note:ui",
    ":feature:note:domain:impl",
    ":feature:note:domain:api",
    ":feature:note:data"
)
 