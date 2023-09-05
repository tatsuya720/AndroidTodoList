plugins {
    id("todolist.android.feature")
    id("todolist.android.library.compose")
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

    implementation(libs.constraintlayout)
    implementation(libs.coroutine.core)
    implementation(libs.coroutine)
    implementation(libs.viewModel.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)

}