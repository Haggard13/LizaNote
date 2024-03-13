plugins {
    alias(libs.plugins.lizanote.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "feature.note.data"
}

dependencies {
    implementation(projects.feature.note.domain.api)
    implementation(projects.feature.notes.domain.api)
    implementation(libs.androidx.room)
    ksp(libs.androidx.room.compiler)

    testImplementation(libs.bundles.test)
}