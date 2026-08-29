plugins {
    id("finager.android.feature")
}

android {
    namespace = "com.devtorres.home"
}

dependencies {
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
}