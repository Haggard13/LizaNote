plugins {
    alias(libs.plugins.lizanote.kotlin)
}

dependencies {
    api(projects.feature.notes.domain.api)
}