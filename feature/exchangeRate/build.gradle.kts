plugins {
    id("wirebarley.android.library")
}

android {
    namespace = "com.example.wirebarley.feature.exchangeRate"

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    testImplementation(project(":core:test"))
    androidTestImplementation(project(":core:test"))
}