plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.compose.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.hilt.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "finager.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "finager.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidCompose") {
            id = "finager.android.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
        register("kotlinLibrary") {
            id = "finager.kotlin.library"
            implementationClass = "KotlinLibraryConventionPlugin"
        }
        register("androidRoom") {
            id = "finager.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("androidHilt") {
            id = "finager.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("androidFeature") {
            id = "finager.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
    }
}
