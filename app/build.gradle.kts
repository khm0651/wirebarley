plugins {
    id("wirebarley.android.application")
    id("wirebarley.android.hilt")
}

android {
    namespace = "com.example.wirebarley"

    defaultConfig {
        applicationId = "com.example.wirebarley"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":feature:exchangeRate"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.android.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    androidTestImplementation(project(":core:test"))
    testImplementation(project(":core:test"))
}