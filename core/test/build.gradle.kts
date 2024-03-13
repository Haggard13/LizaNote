plugins {
    alias(libs.plugins.lizanote.kotlin)
}

dependencies {
    implementation(libs.bundles.test)
    implementation(libs.kotlinx.coroutines.test)
}