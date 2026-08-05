import com.android.build.gradle.LibraryExtension
import extensions.AndroidConfig
import extensions.configureJavaCompatibility
import extensions.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.library")
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.configure<LibraryExtension> {
                compileSdk = AndroidConfig.COMPILE_SDK
                defaultConfig.minSdk = AndroidConfig.MIN_SDK
                configureJavaCompatibility()
            }

            configureKotlinAndroid()
        }
    }
}
