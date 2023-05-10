plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.erix.course.philipp.tracker_domain"
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
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {

    implementation(project(Modules.TRACKER_DATA))
    implementation(project(Modules.MODELS))
    implementation(project(Modules.CORE))

    //Hilt
    implementation(Hilt.ANDROID.android)
    kapt(Hilt.COMPILER.compiler)

}