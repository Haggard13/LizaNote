plugins {
    alias(libs.plugins.lizanote.compose)
}

android {
    namespace = "core.navigation.utils"
}

dependencies {
    implementation(projects.core.navigation.api)

    implementation(libs.gson)
}