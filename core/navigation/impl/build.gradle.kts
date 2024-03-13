plugins {
    alias(libs.plugins.lizanote.compose)
}

android {
    namespace = "core.navigation.impl"
}

dependencies {
    implementation(projects.core.navigation.api)
    implementation(libs.androidx.compose.navigation)
    implementation(libs.gson)
}