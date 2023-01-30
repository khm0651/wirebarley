plugins {
    id("wirebarley.android.library")
}

android {
    namespace = "com.example.wirebarley.core.test"
}

dependencies {
    api(libs.junit4)
    api(libs.androidx.test.ext)
    api(libs.androidx.test.espresso.core)
    api(libs.kotlinx.coroutines.test)
    api(libs.turbine)
}