plugins {
    id("finager.android.library")
    id("finager.android.compose")
}

android {
    namespace = "com.devtorres.ui"
}

dependencies {
    api(project(":core:domain"))
    api(libs.androidx.material.icons.extended)
    api(libs.compose.icons.fontawesome)
}