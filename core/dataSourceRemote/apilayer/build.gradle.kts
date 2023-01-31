plugins {
    id("wirebarley.android.library")
    id("wirebarley.android.hilt")
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.wirebarley.core.remoteDataSource.apilayer"

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.kotlin.serialization)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(project(":core:test"))
}