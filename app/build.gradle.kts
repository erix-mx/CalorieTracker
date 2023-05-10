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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    //Modules
    implementation(project(Modules.MODELS))
    implementation(project(Modules.CORE))
    implementation(project(Modules.CORE_UI))
    implementation(project(Modules.ONBOARDING_PRESENTATION))

    //Hilt
    implementation(Hilt.ANDROID.android)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.3.1")
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    androidTestImplementation(platform("androidx.compose:compose-bom:2022.10.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
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

    //Accompanist
    implementation(Accompanist.SystemUiController.SYSTEM_CONTROLLER)


    //Test
    testImplementation(Junit.Junit.JUNIT)
    androidTestImplementation(Junit.Junit.JUNIT_EXT)

    androidTestImplementation(Expresso.Core.EXPRESSO)

    androidTestImplementation(platform(Compose.Ui.COMPOSE_BOM))
    androidTestImplementation(Compose.Test.JUNIT4)

    debugImplementation(Compose.Test.TOOLING)
    debugImplementation(Compose.Test.MANIFEST)
}

kapt {
    correctErrorTypes = true
}