plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = "com.timrashard.weathr"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.timrashard.weathr"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "AIR_POLLUTION_API_KEY", "\"${project.findProperty("AIR_POLLUTION_API_KEY")}\"")
        buildConfigField("String", "WEATHER_API_KEY", "\"${project.findProperty("WEATHER_API_KEY")}\"")
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
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.play.services.location)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Dagger - Hilt
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.hilt.navigation.compose)

    // Retrofit - Gson
    implementation(libs.retrofit)
    implementation(libs.gson)

    // Navigation
    implementation(libs.androidx.navigation.compose)
    implementation(libs.accompanist.navigation.animation)

    // Coil
    implementation(libs.coil.compose)

    // Lottie
    implementation(libs.lottie)
    implementation(libs.ui)
    implementation(libs.ui.tooling)
    implementation(libs.androidx.foundation)
    implementation(libs.lottie.compose)
}