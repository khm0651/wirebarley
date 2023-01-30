plugins {
    id("wirebarley.android.library")
    id("wirebarley.android.hilt")
}

android {
    namespace = "com.example.wirebarley.core.domain"
}

dependencies {

    implementation(libs.kotlinx.coroutines.android)
    testImplementation(project(":core:test"))
}