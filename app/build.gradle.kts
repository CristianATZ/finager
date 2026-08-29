plugins {
    id("finager.android.application")
    id("finager.android.compose")
    id("finager.android.hilt")
    id("finager.android.navigation3")
}

android {
    namespace = "com.devtorres.finager"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
    implementation(project(":feature:onboarding"))
    implementation(project(":feature:home"))

    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.core.splashscreen)
}