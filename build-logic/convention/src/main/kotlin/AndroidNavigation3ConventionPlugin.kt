import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.findByType
import org.gradle.kotlin.dsl.getByType

class AndroidNavigation3ConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.kotlin.plugin.serialization")

            check(extensions.findByType<LibraryExtension>() != null || extensions.findByType<ApplicationExtension>() != null) {
                "finager.android.hilt requires finager.android.library or finager.android.application"
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

            dependencies {
                "implementation"(libs.findLibrary("kotlinx.serialization.core").get())
                "implementation"(libs.findLibrary("androidx.lifecycle.viewmodel.navigation3").get())
                "implementation"(libs.findLibrary("androidx.navigation3.runtime").get())
                "implementation"(libs.findLibrary("androidx.navigation3.ui").get())
            }
        }
    }
}