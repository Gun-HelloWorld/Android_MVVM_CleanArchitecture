plugins {
    id("com.android.application") version ("7.4.1") apply false
    id("com.android.library") version ("7.4.1") apply false
    id("org.jetbrains.kotlin.android") version ("1.8.0") apply false

    // AAC Navigation
    id("androidx.navigation.safeargs.kotlin") version (Dependencies.VERSION_ANDROIDX_NAVIGATION) apply false

    // Hilt
    id("com.google.dagger.hilt.android") version (Dependencies.VERSION_HILT) apply false
}
