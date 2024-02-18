plugins {
    alias(libs.plugins.lizanote.android)
}

android {
    namespace = "core.viewmodel"
}

dependencies {
    implementation(libs.androidx.viewmodel)
    implementation(projects.core.navigation)
}