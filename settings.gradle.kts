pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") {
            library("appcompat","androidx.appcompat","appcompat").version("1.4.1")
            library("material","com.google.android.material","material").version("1.6.0")
            library("constraintlayout","androidx.constraintlayout","constraintlayout").version("2.1.4")
            library("coordinatorlayout","androidx.coordinatorlayout", "coordinatorlayout").version("1.2.0")
            library("junit", "junit", "junit").version("4.13.2")
            library("junit-ext", "androidx.test.ext", "junit").version("1.1.3")
            library("espresso", "androidx.test.espresso", "espresso-core").version("3.4.0")
            library("fragment-ktx", "androidx.fragment", "fragment-ktx").version("1.2.2")
            library("activity-ktx", "androidx.activity", "activity-ktx").version("1.4.0")
            library("viewModel-ktx", "androidx.lifecycle","lifecycle-viewmodel-ktx").version("2.5.0")
            library("liveData-ktx", "androidx.lifecycle", "lifecycle-livedata-ktx").version("2.5.0")

            version("navigation-version", "2.5.0")
            library("navigation-fragment-ktx", "androidx.navigation","navigation-fragment-ktx").versionRef("navigation-version")
            library("navigation-ui-ktx", "androidx.navigation","navigation-ui-ktx").versionRef("navigation-version")
            bundle("navigation", listOf("navigation-fragment-ktx", "navigation-ui-ktx"))

            library("navigation-safe-args","androidx.navigation", "navigation-safe-args-gradle-plugin").versionRef("navigation-version")

            library("material3", "androidx.compose.material3", "material3").version("1.0.1")
        }
    }
}

rootProject.name = "TodoList"
include(":app")
include(":feature:list")
include(":feature:edit")
include(":core:data")
include(":core:domain")
include(":core:common")
include(":core:navigator")
include(":core:database")
include(":core:ui")
