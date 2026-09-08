plugins {
    id("finager.android.feature")
}

android {
    namespace = "com.devtorres.splash"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(libs.androidx.core.ktx)
}