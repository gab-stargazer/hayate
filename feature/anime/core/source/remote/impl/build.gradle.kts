import com.lelestacia.hayate.buildsrc.ProjectConfig
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.junit.adapter)
    alias(libs.plugins.apollo)
    alias(libs.plugins.secret.gradle)
}

android {
    namespace = "com.lelestacia.hayate.feature.anime.core.source.remote.impl"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }

    sourceSets {
        named("main") {
            java.srcDir(listOf("src/main/java", "src/main/graphql"))
            resources.srcDir("src/main/resources")
        }
    }

    buildFeatures {
        buildConfig = true
    }
}

apollo {
    service("characters") {
        packageName.set("com.lelestacia.hayate.feature.anime.core.source.remote.impl")
        generateKotlinModels.set(true)
        sourceFolder.set("com/lelestacia/hayate/feature/anime/core/source/remote/impl/character")
    }
}

dependencies {

    //  =====API=====
    implementation(projects.core.common)
    implementation(projects.feature.anime.core.source.remote.api)

    //  Apollo
    implementation(libs.apollo.runtime)

    //  Coroutine
    implementation(libs.coroutine)

    //  Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    //  Logging Interceptor
    implementation(libs.logging.interceptor)

    //  Paging
    implementation(libs.paging)

    //  Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)

    //  Timber
    implementation(libs.timber)

    //  Ui Test
    androidTestImplementation(libs.ui.test.espresso.core)
    androidTestImplementation(libs.ui.test.android.junit)

    /*============================ Testing ==============================*/
    // (Required) Writing and executing Unit Tests on the JUnit Platform
    testImplementation(libs.junit)
    testRuntimeOnly(libs.junit.engine)

    // (Optional) If you need "Parameterized Tests"
    testImplementation(libs.junit.parameterized)

    // (Optional) If you also have JUnit 4-based tests
    testImplementation(libs.test.junit.old)
    testRuntimeOnly(libs.junit.vintage.engine)
    /*====================================================================*/
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + "-Xcontext-receivers"
    }
}