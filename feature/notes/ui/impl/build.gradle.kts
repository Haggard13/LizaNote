plugins {
    alias(libs.plugins.lizanote.compose)
    alias(libs.plugins.ksp)
}

android {
    namespace = "feature.notes.ui"
}

dependencies {
    implementation(projects.feature.notes.domain.api)
    implementation(projects.feature.notes.ui.api)

    implementation(projects.core.uikit)
    implementation(projects.core.viewmodel)
    implementation(projects.core.navigation.api)
    implementation(projects.flows)

    compileOnly(projects.core.viewmodelEvent.annotations)
    ksp(projects.core.viewmodelEvent.processor)

    testImplementation(libs.bundles.test)
    testImplementation(projects.core.test)
}