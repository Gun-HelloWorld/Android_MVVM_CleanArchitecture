object Dependencies {

    private const val VERSION_GOOGLE_MATERIAL = "1.8.0"
    private const val VERSION_JUNIT = "4.13.2"
    private const val VERSION_JUNIT_ANDROID = "1.1.5"
    private const val VERSION_ESPRESSO = "3.5.1"
    private const val VERSION_ANDROIDX_CORE = "1.7.0"
    private const val VERSION_ANDROIDX_APPCOMPAT = "1.6.1"
    private const val VERSION_ANDROIDX_CONSTRAINT_LAYOUT = "2.1.4"
    private const val VERSION_ANDROIDX_NAVIGATION = "2.5.3"
    private const val VERSION_GLIDE = "4.15.1"
    private const val VERSION_DOTS_INDICATOR = "4.3"                                                // For ViewPager

    object Google {
        const val MATERIAL = "com.google.android.material:material:${VERSION_GOOGLE_MATERIAL}"
    }

    object Test {
        const val JUNIT = "junit:junit:${VERSION_JUNIT}"
        const val JUNIT_ANDROID = "androidx.test.ext:junit:${VERSION_JUNIT_ANDROID}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${VERSION_ESPRESSO}"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:${VERSION_ANDROIDX_CORE}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${VERSION_ANDROIDX_APPCOMPAT}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${VERSION_ANDROIDX_CONSTRAINT_LAYOUT}"

        // Navigation
        const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${VERSION_ANDROIDX_NAVIGATION}"
        const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${VERSION_ANDROIDX_NAVIGATION}"
    }

    object Glide {
        const val GLIDE = "com.github.bumptech.glide:glide:${VERSION_GLIDE}"
        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${VERSION_GLIDE}"
    }

    object DotsIndicator {
        const val DOTS_INDICATOR = "com.tbuonomo:dotsindicator:${VERSION_DOTS_INDICATOR}"
    }

}