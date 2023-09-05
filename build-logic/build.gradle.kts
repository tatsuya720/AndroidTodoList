plugins {
    `kotlin-dsl`
}

group = "buildlogic.extensions"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "todolist.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidLibrary") {
            id = "todolist.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("androidFeature") {
            id = "todoList.android.feature"
            implementationClass = "AndroidFeaturePlugin"
        }
    }
}