import org.gradle.api.Plugin
import org.gradle.api.Project

class LizaNoteKotlinModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("kotlin")
            pluginManager.apply("org.jetbrains.kotlin.jvm")
        }
    }
}