// Top-level build file where you can add configuration options common to all sub-projects/modules.
@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.junit.adapter) apply false
    alias(libs.plugins.parcelize) apply false
    alias(libs.plugins.firebase) apply false
    alias(libs.plugins.firebase.crashlytic) apply false
    alias(libs.plugins.androidTest) apply false
    alias(libs.plugins.baseline.profile) apply false
}
true // Needed to make the Suppress annotation work for the plugins block