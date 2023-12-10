import com.lelestacia.hayate.buildsrc.ProjectConfig

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.lelestacia.hayate.feature.anime.core.common"
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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {

    //=====App Shared=====
    implementation(projects.common.shared)
    implementation(projects.common.theme)

    //=====Feature Anime=====
    implementation(projects.feature.anime.core.domain)

    //=====Compose=====
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material.design)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.navigation)

    //=====Coil=====
    implementation(libs.coil)

    //=====Paging=====
    implementation(libs.paging)
    implementation(libs.paging.compose)
}