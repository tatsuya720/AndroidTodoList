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
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:navigator"))
    implementation(project(":core:domain"))

    implementation(libs.constraintlayout)
    implementation(libs.coordinatorlayout)
    implementation(libs.fragment.ktx)
    implementation(libs.activity.ktx)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation(libs.coroutine)
    implementation(libs.viewModel.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    annotationProcessor(libs.room.compiler)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

}