plugins {
    id("wirebarley.android.library")
    id("wirebarley.android.hilt")
}

android {
    namespace = "com.example.wirebarley.core.common"
}

dependencies {
    testImplementation(project(":core:test"))
}