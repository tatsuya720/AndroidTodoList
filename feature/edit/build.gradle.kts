plugins {
    id("todolist.android.feature")
    id("todolist.android.library.compose")
}

android {
    namespace = "com.example.edit"

    buildFeatures {
        viewBinding = true
    }

}

dependencies {

    implementation(project(":core:ui"))
    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:navigator"))
    implementation(project(":core:domain"))

    implementation(libs.constraintlayout)
    implementation(libs.coordinatorlayout)
//    implementation(libs.fragment.ktx)
//    implementation(libs.activity.ktx)
//    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)
}