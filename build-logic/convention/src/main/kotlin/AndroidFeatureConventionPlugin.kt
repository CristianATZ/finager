import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("finager.android.library")
            pluginManager.apply("finager.android.compose")
            pluginManager.apply("finager.android.hilt")
            pluginManager.apply("finager.android.navigation3")

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                "implementation"(libs.findLibrary("androidx.activity.compose").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel.ktx").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
                "implementation"(libs.findLibrary("androidx.hilt.navigation.compose").get())
            }
        }
    }
}