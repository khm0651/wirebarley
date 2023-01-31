plugins {
    id("wirebarley.android.library")
    id("wirebarley.android.hilt")
}

android {
    namespace = "com.example.wirebarley.core.data"
}

dependencies {
    implementation(project(":core:dataSourceLocal:inMemory"))
    implementation(project(":core:dataSourceLocal:room"))
    implementation(project(":core:dataSourceRemote:apilayer"))
    implementation(project(":core:model"))

    testImplementation(project(":core:test"))
}