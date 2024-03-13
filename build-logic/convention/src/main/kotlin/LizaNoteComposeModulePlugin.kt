import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import ru.eh13.lizanote.gradle.libs

class LizaNoteComposeModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(LizaNoteAndroidModulePlugin::class)
            }

            extensions.configure<LibraryExtension> {
                buildFeatures {
                    compose = true
                }
                composeOptions {
                    kotlinCompilerExtensionVersion = "1.5.10"
                }

                tasks.withType<KotlinCompile>().configureEach {
                    kotlinOptions {
                        freeCompilerArgs = freeCompilerArgs + listOf(
                            "-P",
                            "plugin:androidx.compose.compiler.plugins.kotlin:stabilityConfigurationPath=" +
                                "${path}/compose_compiler_config.conf"
                        )
                    }
                }
            }
            dependencies {
                add("implementation", platform(libs.findLibrary("androidx.compose.bom").get()))
                add("implementation", libs.findBundle("compose").get())
            }
        }
    }
}