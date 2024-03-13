plugins {
    alias(libs.plugins.lizanote.kotlin)
}

dependencies {
    implementation(projects.feature.note.domain.api)

    testImplementation(libs.bundles.test)
}