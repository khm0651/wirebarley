plugins {
    id("wirebarley.android.library")
    id("wirebarley.android.hilt")
}

android {
    namespace = "com.example.wirebarley.core.localDataSource.inMemory"

}

dependencies {
    implementation(project(":core:model"))

    testImplementation(project(":core:test"))
}