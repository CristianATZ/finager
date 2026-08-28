plugins {
    id("finager.android.feature")
}

android {
    namespace = "com.devtorres.onboarding"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
}