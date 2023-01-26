plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 33

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:navigator"))

    implementation(libs.constraintlayout)
    implementation(libs.coroutine.core)
    implementation(libs.coroutine)
    implementation(libs.viewModel.ktx)
    implementation(libs.liveData.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

}