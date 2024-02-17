plugins {
    alias(libs.plugins.lizanote.kotlin)
}

dependencies {
    implementation(libs.ksp)
    implementation(libs.bundles.kotlin.poet)

    implementation(projects.core.viewmodelEvent.annotations)
}