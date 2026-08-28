plugins {
    id("finager.android.application")
    id("finager.android.compose")
}

android {
    namespace = "com.devtorres.finager"
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":feature:onboarding"))

    implementation(libs.androidx.lifecycle.runtime.ktx)
}