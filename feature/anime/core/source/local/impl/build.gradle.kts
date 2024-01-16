import com.lelestacia.hayate.buildsrc.ProjectConfig

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.junit.adapter)
}

android {
    namespace = "com.lelestacia.hayate.feature.anime.core.source.local.impl"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        minSdk = ProjectConfig.minSdk


        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")

        javaCompileOptions {
            ksp {
                arg("room.schemaLocation", "$projectDir/schemas")
                arg("room.incremental", "true")
            }
        }

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
}

dependencies {

    //  =====API=====
    implementation(projects.feature.anime.core.source.local.api)
    implementation(projects.common.shared)

    //  Coroutine
    implementation(libs.coroutine)

    //  Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)

    //  Paging
    implementation(libs.paging)

    // Room
    implementation(libs.room)
    implementation(libs.room.ktx)
    implementation(libs.room.paging)
    ksp(libs.room.compiler)

    //  Timber
    implementation(libs.timber)

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