plugins {
    id("todoList.android.feature")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.list"

    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation(project(":core:data"))
    implementation(project(":core:domain"))
    implementation(project(":core:common"))
    implementation(project(":core:navigator"))
    implementation(project(":core:ui"))

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

    implementation(libs.constraintlayout)
    implementation(libs.coroutine.core)
    implementation(libs.coroutine)
    implementation(libs.viewModel.ktx)
    implementation(libs.liveData.ktx)

    implementation(libs.viewModel.compose)
    implementation(libs.hilt.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)

    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

}