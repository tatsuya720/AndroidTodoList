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

    implementation(project(":common"))
    implementation(project(":navigator"))

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
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