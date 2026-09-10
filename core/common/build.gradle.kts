plugins {
    id("finager.android.library")
}

android {
    namespace = "com.devtorres.common"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.runtime)
}