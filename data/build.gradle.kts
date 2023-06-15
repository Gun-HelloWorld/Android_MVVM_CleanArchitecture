import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")                            // Hilt
}

android {
    compileSdk = AppConfig.COMPILE_SDK

    defaultConfig {
        minSdk = AppConfig.MIN_SDK
        targetSdk = AppConfig.TARGET_SDK

        testInstrumentationRunner = AppConfig.TEST_INSTRUMENTATION_RUNNER

        buildConfigField("String", AppConfig.API_PUBLIC_KEY, getApiKey(AppConfig.API_PUBLIC_KEY))
        buildConfigField("String", AppConfig.API_PRIVATE_KEY, getApiKey(AppConfig.API_PRIVATE_KEY))
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
}

dependencies {
    implementation(project(":domain"))

    implementation(Dependencies.Google.MATERIAL)

    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.Test.JUNIT_ANDROID)
    androidTestImplementation(Dependencies.Test.ESPRESSO)

    implementation(Dependencies.AndroidX.CORE)
    implementation(Dependencies.AndroidX.APP_COMPAT)

    // Hilt
    implementation(Dependencies.Google.HILT)
    kapt(Dependencies.Google.HILT_COMPILER)

    // Retrofit
    implementation(Dependencies.Retrofit.RETROFIT)
    implementation(Dependencies.Retrofit.RETROFIT_CONVERTOR)

    // OkHttp
    implementation(Dependencies.OkHttp.OKHTTP)
    implementation(Dependencies.OkHttp.OKHTTP_INTERCEPTOR)

    // Gson
    implementation(Dependencies.Gson.GSON)

    // Paging
    implementation(Dependencies.AndroidX.PAGING_COMMON)
}

// Hilt
kapt {
    correctErrorTypes = true
}

fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}