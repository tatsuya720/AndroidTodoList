plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.ui"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

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

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.8.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    val bom = platform(libs.compose.bom)
    implementation(bom)
    androidTestImplementation(bom)
    api("androidx.compose.compiler:compiler:1.3.2")
    api("androidx.compose.foundation:foundation:1.2.0-rc02")
    debugApi("androidx.compose.ui:ui-tooling:1.2.0-rc02")
    api("androidx.compose.ui:ui-tooling-preview:1.2.0-rc02")
    api("androidx.compose.material:material-icons-core")
    api("androidx.compose.material3:material3-window-size-class")
    api("androidx.compose.runtime:runtime")
    api("androidx.compose.runtime:runtime-livedata")


    api(libs.navigation.compose)

}