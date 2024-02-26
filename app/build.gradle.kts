import com.lelestacia.hayate.buildsrc.ProjectConfig

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.junit.adapter)
    alias(libs.plugins.firebase)
    alias(libs.plugins.firebase.crashlytic)
    alias(libs.plugins.baseline.profile)
}

android {
    namespace = "com.lelestacia.hayate"
    compileSdk = ProjectConfig.compileSdk

    defaultConfig {
        applicationId = "com.lelestacia.hayate"
        minSdk = ProjectConfig.minSdk
        targetSdk = ProjectConfig.targetSdk
        versionCode = 3
        versionName = "1.0.2"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            applicationIdSuffix = ".stable"
        }

        create("r8_testing") {
            initWith(buildTypes.getByName("release"))
            isDebuggable = false
            isProfileable = true
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("debug")
            matchingFallbacks += listOf("release")
            applicationIdSuffix = ".testing"
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = ProjectConfig.jvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    splits {
        abi {
            isEnable = true
            reset()
            include("x86", "x86_64", "armeabi-v7a", "arm64-v8a")
            isUniversalApk = false
        }
    }
}

dependencies {

    implementation(projects.core.common)
    implementation(projects.core.theme)

    //  =====Feature Anime=====
    implementation(projects.feature.anime.core.data)
    implementation(projects.feature.anime.core.domain)
    implementation(projects.feature.anime.core.source.remote.impl)
    implementation(projects.feature.anime.core.source.local.impl)

    //  Exploration Module
    implementation(projects.feature.anime.exploration.ui)

    //  Detail Module
    implementation(projects.feature.anime.detail.ui)

    //  Collection Module
    implementation(projects.feature.anime.collection.ui)

    //  Search Module
    implementation(projects.feature.anime.search.ui)

    //  Init Module
    implementation(projects.feature.anime.initialization.ui)

    //  Setting Module
    implementation(projects.feature.settings.ui)

    //  Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytic)
    implementation(projects.core.firebase)

    //  Ktx
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.viewmodel.compose)
    implementation(libs.lifecycle.viewmodel.runtime.compose)

    //  Accompanist
    implementation(libs.accompanist.system.ui.controller)

    //  Coil
    implementation(libs.coil)

    //  Compose Activity
    implementation(libs.compose.activity)

    //  Compose BOM
    implementation(platform(libs.compose.bom))
    implementation(libs.profile.installer)
    androidTestImplementation(platform(libs.compose.bom))

    //  Compose
    implementation(libs.compose.material.design)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphic)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.icon)
    implementation(libs.compose.font)
    implementation(libs.compose.icons.font.awesome)

    //  Coroutine
    implementation(libs.coroutine)

    //  Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytic)

    //  Hilt
    implementation(libs.hilt)
    implementation(libs.hilt.compose)
    "baselineProfile"(projects.baselineProfiler)
    ksp(libs.hilt.compiler)

    //  Logging Interceptor
    implementation(libs.logging.interceptor)

    //  Navigation
    implementation(libs.navigation)

    //  Paging
    implementation(libs.paging)
    implementation(libs.paging.compose)

    //  Retrofit
    implementation(libs.retrofit)
    implementation(libs.retrofit.moshi)
    implementation(libs.moshi)
    ksp(libs.moshi.codegen)

    //  Room
    implementation(libs.room)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)

    //  Timber
    implementation(libs.timber)

    //  Ui Test
    androidTestImplementation(libs.ui.test.espresso.core)
    androidTestImplementation(libs.ui.test.android.junit)
    androidTestImplementation(libs.ui.test.compose.junit)
    androidTestImplementation(libs.ui.test.compose.manifest)

    //  ViewModel
    implementation(libs.lifecycle.ktx)
    implementation(libs.lifecycle.viewmodel)
    implementation(libs.lifecycle.viewmodel.compose)

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

    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation("androidx.test:runner:1.4.0")
    androidTestImplementation("androidx.test:rules:1.4.0")

    // Test rules and transitive dependencies:
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.6.1")
    // Needed for createAndroidComposeRule, but not createComposeRule:
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.1")

}