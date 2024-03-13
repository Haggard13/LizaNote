plugins {
    alias(libs.plugins.lizanote.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "feature.note.ui"
}

dependencies {
    implementation(projects.core.viewmodel)
    implementation(projects.core.navigation.api)
    implementation(projects.core.uikit)

    implementation(projects.flows)

    implementation(projects.feature.note.domain.api)
    implementation(projects.feature.notes.ui.api)

    compileOnly(projects.core.viewmodelEvent.annotations)
    ksp(projects.core.viewmodelEvent.processor)

    testImplementation(libs.bundles.test)
    testImplementation(projects.core.test)
    testImplementation(projects.feature.notes.ui.impl)
    testImplementation(projects.feature.note.domain.impl)
}