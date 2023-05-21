
plugins {

    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.erix.course.philipp.core_ui"
    compileSdk = Project.compileSdk

    defaultConfig {
        minSdk = Project.minSdk
        targetSdk = Project.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Compose.Versions.COMPILER
    }
}

dependencies {


    //Compose
    implementation(Compose.Activity.COMPOSE)
    implementation(platform(Compose.Ui.COMPOSE_BOM))
    implementation(Compose.Ui.UI)
    implementation(Compose.Ui.UI_GRAPHICS)
    implementation(Compose.Ui.UI_TOOLING_PREVIEW)
    implementation(Compose.Ui.MATERIAL3)
    implementation(Compose.Navigation.NAV_HOST)
    implementation(Compose.Ui.MATERIAL)

    //Accompanist
    implementation(Accompanist.SystemUiController.SYSTEM_CONTROLLER)


    //Test
    testImplementation(Junit.Junit.JUNIT)
    androidTestImplementation(Junit.Junit.JUNIT_EXT)

    //Debug
    debugImplementation(Compose.Test.TOOLING)
    debugImplementation(Compose.Test.MANIFEST)
}