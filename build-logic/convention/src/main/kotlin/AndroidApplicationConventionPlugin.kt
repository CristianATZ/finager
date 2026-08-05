import com.android.build.api.dsl.ApplicationExtension
import extensions.AndroidConfig
import extensions.configureJavaCompatibility
import extensions.configureKotlinAndroid
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.android.application")
            pluginManager.apply("org.jetbrains.kotlin.android")

            extensions.configure<ApplicationExtension> {
                compileSdk = AndroidConfig.COMPILE_SDK
                defaultConfig {
                    minSdk = AndroidConfig.MIN_SDK
                    targetSdk = AndroidConfig.TARGET_SDK
                }
                configureJavaCompatibility()
            }

            configureKotlinAndroid()
        }
    }
}
