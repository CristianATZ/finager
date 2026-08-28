plugins {
    id("finager.android.library")
    id("finager.android.room")
}

android {
    namespace = "com.devtorres.data"
}

dependencies {
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.androidx.datastore.preferences.core)
}