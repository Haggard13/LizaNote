import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

class LizaNoteKotlinModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("kotlin")
            pluginManager.apply("org.jetbrains.kotlin.jvm")
            dependencies {
                add("implementation", "org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.8.0")
                add("testImplementation", "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
            }
        }
    }
}