@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("androidx.navigation.safeargs.kotlin")    // AAC Navigation
    id("kotlin-kapt")                            // Glide, Hilt
    id("com.google.dagger.hilt.android")         // Hilt
    id("kotlin-parcelize")
}

android {
    namespace = AppConfig.NAME_SPACE
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        applicationId = "com.gun.mvvm_cleanarchitecture"
        minSdk = AppConfig.MIN_SDK
        targetSdk = AppConfig.TARGET_SDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = AppConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile(AppConfig.PROGUARD_OPTIMIZE), AppConfig.PROGUARD)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    dataBinding {
        enable = true
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))

    implementation(Dependencies.Google.MATERIAL)
    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.JUNIT_ANDROID)
    androidTestImplementation(Dependencies.Test.ESPRESSO)

    implementation(Dependencies.AndroidX.CORE)
    implementation(Dependencies.AndroidX.APP_COMPAT)
    implementation(Dependencies.AndroidX.CONSTRAINT_LAYOUT)

    // AAC Navigation
    implementation(Dependencies.AndroidX.NAVIGATION_FRAGMENT)
    implementation(Dependencies.AndroidX.NAVIGATION_UI)

    // Glide
    implementation(Dependencies.Glide.GLIDE)
    kapt(Dependencies.Glide.GLIDE_COMPILER)

    // ViewPager Indicator
    implementation(Dependencies.DotsIndicator.DOTS_INDICATOR)

    //Hilt
    implementation(Dependencies.Google.HILT)
    kapt(Dependencies.Google.HILT_COMPILER)

    // Retrofit
    implementation(Dependencies.Retrofit.RETROFIT)
    implementation(Dependencies.Retrofit.RETROFIT_CONVERTOR)

    // OkHttp
    implementation(Dependencies.OkHttp.OKHTTP)
    implementation(Dependencies.OkHttp.OKHTTP_INTERCEPTOR)

    //Gson
    implementation(Dependencies.Gson.GSON)

    // RecyclerView (with ConcatAdapter)
    implementation(Dependencies.AndroidX.RECYCLER_VIEW)

    // CardView
    implementation(Dependencies.AndroidX.CARD_VIEW)

    // ShimmerEffect
    implementation(Dependencies.ShimmerEffect.SHIMMER_EFFECT)
}

// Hilt
kapt {
    correctErrorTypes = true
}