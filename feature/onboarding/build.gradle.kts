plugins {
    id("finager.android.library")
    id("finager.android.compose")
}

android {
    namespace = "com.devtorres.onboarding"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
}