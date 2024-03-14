plugins {
    alias(libs.plugins.lizanote.compose)
}

android {
    namespace = "feature.note.flow"
}

dependencies {
    implementation(projects.core.navigation.api)
    implementation(projects.core.navigation.utils)

    implementation(projects.flows)

    implementation(projects.feature.notes.ui.impl)
    implementation(projects.feature.notes.ui.api)

    api(projects.feature.note.ui)
    implementation(projects.feature.note.domain.api)
    implementation(projects.feature.note.domain.impl)
    api(projects.feature.note.data)
}