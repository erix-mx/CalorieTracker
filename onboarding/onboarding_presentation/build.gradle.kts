plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.erix.course.philipp.onboarding_presentation"
    compileSdk = Project.compileSdk

    defaultConfig {
        minSdk = Project.minSdk
        targetSdk = Project.targetSdk

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.Versions.COMPILER
    }

    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    //Hilt
    implementation(Hilt.ANDROID.android)
    kapt(Hilt.COMPILER.compiler)

    //Hilt Compose
    implementation(Hilt.Fragment.navigation)

    //Modules
    implementation(project(Modules.CORE))
    implementation(project(Modules.CORE_UI))
    implementation(project(Modules.ONBOARDING_DOMAIN))

    //AndroidX
    implementation(AndroidX.Core.KTX)
    implementation(AndroidX.Lifecycle.RUNTIME)

    //Lottie
    implementation(Lottie.LOTTIE)

    //Compose
    implementation(Compose.Activity.COMPOSE)
    implementation(platform(Compose.Ui.COMPOSE_BOM))
    implementation(Compose.Ui.UI)
    implementation(Compose.Ui.UI_GRAPHICS)
    implementation(Compose.Ui.UI_TOOLING_PREVIEW)
    implementation(Compose.Ui.MATERIAL)
    implementation(Compose.Ui.MATERIAL3)

    //Test
    testImplementation(Junit.Junit.JUNIT)
    androidTestImplementation(Junit.Junit.JUNIT_EXT)

    androidTestImplementation(platform(Compose.Ui.COMPOSE_BOM))
    androidTestImplementation(Compose.Test.JUNIT4)

    debugImplementation(Compose.Test.TOOLING)
    debugImplementation(Compose.Test.MANIFEST)
}