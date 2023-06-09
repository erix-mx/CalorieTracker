plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")

    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp").version("1.6.10-1.0.4")
}

android {
    namespace = "com.erix.tracker_data"
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

    kapt {
        generateStubs = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {

    //Models
    implementation(project(Modules.MODELS))

    //Hilt
    implementation(Hilt.ANDROID.android)
    kapt(Hilt.COMPILER.compiler)

    //AndroidX
    implementation(AndroidX.Core.KTX)
    implementation(AndroidX.Lifecycle.RUNTIME)

    //Retrofit
    implementation(Retrofit.RETROFIT)
    implementation(Retrofit.LOGGER_INTERCEPTOR)
    
    implementation(Retrofit.MOCHI_CONVERTER)


    //Room
    implementation(Room.runtime)
    kapt(Room.compiler)
    implementation(Room.ktx)



    //Test
    testImplementation(Junit.Junit.JUNIT)
    androidTestImplementation(Junit.Junit.JUNIT_EXT)
    testImplementation(Mockk.MOCKK)
    testImplementation(Mockk.MOCK.WEBSERVER)

}