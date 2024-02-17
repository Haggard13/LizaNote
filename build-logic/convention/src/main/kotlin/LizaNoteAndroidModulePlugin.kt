import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import ru.eh13.lizanote.gradle.libs

class LizaNoteAndroidModulePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply("org.jetbrains.kotlin.android")
            }

            extensions.configure<LibraryExtension> {
                compileSdk = 34
                defaultConfig {
                    minSdk = 26
                }
                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                    isCoreLibraryDesugaringEnabled = true
                }

                testOptions.targetSdk = 34
                lint.targetSdk = 34

                tasks.withType<KotlinCompile>().configureEach {
                    kotlinOptions {
                        jvmTarget = JavaVersion.VERSION_11.toString()
                        val warningsAsErrors: String? by project
                        allWarningsAsErrors = warningsAsErrors.toBoolean()
                        freeCompilerArgs = freeCompilerArgs + listOf(
                            "-opt-in=kotlin.RequiresOptIn",
                            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                            "-opt-in=kotlinx.coroutines.FlowPreview",
                        )
                    }
                }
            }
            dependencies {
                add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
            }
        }
    }
}