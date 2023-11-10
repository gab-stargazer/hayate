@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.junit.adapter)
}

android {
    namespace = "com.lelestacia.hayate.common.shared"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    //  Ktx
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    //  Coil
    implementation(libs.coil)

    //  Compose Activity
    implementation(libs.compose.activity)

    //  Compose BOM
    implementation(platform(libs.compose.bom))
    androidTestImplementation(platform(libs.compose.bom))

    //  Compose
    implementation(libs.compose.material.design)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.graphic)
    implementation(libs.compose.ui.tooling)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.icon)
    implementation(libs.compose.font)

    //  Coroutine
    implementation(libs.coroutine)

    //  Firebase
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.crashlytic)

    //  Hilt
    implementation(libs.hilt)
    implementation(libs.hilt.compose)
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
}