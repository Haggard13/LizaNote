plugins {
    alias(libs.plugins.lizanote.compose)
}

android {
    namespace = "feature.notes.ui.api"
}

dependencies {
    implementation(projects.feature.notes.domain.api)
}