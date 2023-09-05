plugins {
    id("todolist.android.application")
    id("com.google.dagger.hilt.android")
}

android {

    namespace = "com.example.todolist"

     defaultConfig {
        applicationId = "jp.gr.java_conf.fumitsuki_todo"
        versionCode = 3
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        //compose = true
    }

//    composeOptions {
//        kotlinCompilerExtensionVersion = "1.2.0-rc02"
//    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.junit.ext)
    androidTestImplementation(libs.espresso)



    //jetpack compose 関連
    //implementation(libs.activity.compose)


    implementation(libs.hilt)
    kapt(libs.hilt.compiler)

//    val bom = platform(libs.compose.bom)
//    implementation(bom)
//    androidTestImplementation(bom)


    implementation(project(":core:common"))
    implementation(project(":core:data"))
    implementation(project(":core:database"))
    implementation(project(":core:domain"))
    implementation(project(":core:navigator"))
    implementation(project(":core:ui"))

    implementation(project(":feature:list"))
    implementation(project(":feature:edit"))
}