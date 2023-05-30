plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}
android {
    namespace = Project.namespace
    compileSdk = Project.compileSdk

    defaultConfig {
        applicationId   = Project.applicationId
        minSdk          = Project.minSdk
        targetSdk       = Project.targetSdk
        versionCode =  Project.versionCode
        versionName = Project.versionName

        testInstrumentationRunner = "com.erix.course.philipp.calorytracker.HiltTestRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.Versions.COMPILER
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"

        }
    }

}

dependencies {

    //Modules
    implementation(project(Modules.MODELS))
    implementation(project(Modules.CORE))
    implementation(project(Modules.CORE_UI))
    implementation(project(Modules.ONBOARDING_PRESENTATION))
    implementation(project(Modules.TRACKER_PRESENTATION))
    implementation(project(Modules.TRACKER_DATA))
    implementation(project(Modules.TRACKER_DOMAIN))


    //Hilt
    implementation(Hilt.ANDROID.android)
    kapt(Hilt.COMPILER.compiler)

    //Hilt Compose
    implementation(Hilt.Fragment.navigation)

    //AndroidX
    implementation(AndroidX.Core.KTX)
    implementation(AndroidX.Lifecycle.RUNTIME)
    implementation(AndroidX.Navigation.RUNTIME)


    //Compose
    implementation(Compose.Activity.COMPOSE)
    implementation(platform(Compose.Ui.COMPOSE_BOM))
    implementation(Compose.Ui.UI)
    implementation(Compose.Ui.UI_GRAPHICS)
    implementation(Compose.Ui.UI_TOOLING_PREVIEW)
    implementation(Compose.Ui.MATERIAL3)
    implementation(Compose.Navigation.NAV_HOST)
    implementation(Compose.Navigation.NAV_ANIMATION)

    implementation(Compose.Ui.MATERIAL)




    //Test
    testImplementation(Junit.Junit.JUNIT)
    androidTestImplementation(Junit.Junit.JUNIT_EXT)

    androidTestImplementation(Expresso.Core.EXPRESSO)

    androidTestImplementation(platform(Compose.Ui.COMPOSE_BOM))
    androidTestImplementation(Compose.Test.JUNIT4)

    debugImplementation(Compose.Test.TOOLING)
    debugImplementation(Compose.Test.MANIFEST)

    kaptAndroidTest(Hilt.COMPILER.compiler)
    androidTestImplementation(Hilt.Test.hiltTesting)

    androidTestImplementation (Compose.Test.NAVIGATION)

}

kapt {
    correctErrorTypes = true
}