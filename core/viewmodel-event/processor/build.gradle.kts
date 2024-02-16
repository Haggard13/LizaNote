plugins {
    id("kotlin")
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(libs.ksp)
    implementation(libs.bundles.kotlin.poet)

    implementation(projects.core.viewmodelEvent.annotations)
}