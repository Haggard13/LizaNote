plugins {
    alias(libs.plugins.lizanote.kotlin)
}

dependencies {
    implementation(projects.feature.notes.domain.api)
}