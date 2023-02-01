plugins {
    id("wirebarley.android.library")
    id("wirebarley.android.hilt")
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.wirebarley.core.dataSourceLocal.room"

    defaultConfig {
        // Room auto migrations 사용시 필요합니다.
        // https://developer.android.com/reference/kotlin/androidx/room/AutoMigration 참고.
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:model"))

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    implementation(libs.kotlinx.coroutines.android)

    testImplementation(project(":core:test"))
    androidTestImplementation(project(":core:test"))
}