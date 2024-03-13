plugins {
    alias(libs.plugins.lizanote.compose)
}

android {
    namespace = "feature.notes.flow"
}

dependencies {
    implementation(projects.core.navigation.api)
    implementation(projects.core.navigation.utils)

    implementation(projects.flows)

    implementation(projects.feature.notes.domain.api)
    implementation(projects.feature.notes.domain.impl)
    api(projects.feature.notes.ui.impl)
    implementation(projects.feature.notes.ui.api)

    implementation(projects.feature.note.data)
}