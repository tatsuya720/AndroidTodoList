plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 33
}

dependencies {

    implementation(project(":common"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    api(libs.bundles.navigation)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)
}