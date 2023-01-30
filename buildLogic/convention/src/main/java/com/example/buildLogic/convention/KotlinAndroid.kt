import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 * Configure base Kotlin with Android options
 */
fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    commonExtension.apply {
        compileSdk = libs.findVersion("compileSdk").get().toString().toInt()

        defaultConfig {
            minSdk = libs.findVersion("minSdk").get().toString().toInt()
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }

        kotlinOptions {
            // 모든 Kotlin 경고를 오류로 처리 (default = disabled)
            // 사용법 ~/gradle/gradle.properties 에서 warningsAsErrors=true 로 Override
            val warningsAsErrors: String? by project
            allWarningsAsErrors = warningsAsErrors.toBoolean()

            freeCompilerArgs = freeCompilerArgs + listOf(
                // Enable experimental coroutines APIs
                "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
            )

            jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}

fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}

fun Project.getLocalProperty(key: String): String {
    return gradleLocalProperties(rootDir).getProperty(key)
}