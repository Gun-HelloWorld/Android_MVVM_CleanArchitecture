object Dependencies {

    private const val VERSION_GOOGLE_MATERIAL = "1.8.0"
    private const val VERSION_JUNIT = "4.13.2"
    private const val VERSION_JUNIT_ANDROID = "1.1.5"
    private const val VERSION_ESPRESSO = "3.5.1"
    private const val VERSION_ANDROIDX_CORE = "1.7.0"
    private const val VERSION_ANDROIDX_APPCOMPAT = "1.6.1"
    private const val VERSION_ANDROIDX_CONSTRAINT_LAYOUT = "2.1.4"
    private const val VERSION_GLIDE = "4.15.1"
    private const val VERSION_DOTS_INDICATOR = "4.3"                                                // For ViewPager
    private const val VERSION_RETROFIT = "2.9.0"
    private const val VERSION_OKHTTP = "4.10.0"
    private const val VERSION_GSON = "2.10.1"
    private const val VERSION_RECYCLER_VIEW = "1.2.1"
    private const val VERSION_CARD_VIEW = "1.0.0"
    private const val VERSION_SHIMMER_EFFECT = "0.5.0"
    private const val VERSION_COROUTINES_TEST = "1.6.4"
    private const val VERSION_TURBINE = "0.13.0"
    private const val VERSION_PAGING = "3.1.1"

    const val VERSION_ANDROIDX_NAVIGATION = "2.5.3"
    const val VERSION_HILT = "2.45"

    object Google {
        const val MATERIAL = "com.google.android.material:material:${VERSION_GOOGLE_MATERIAL}"
        const val HILT = "com.google.dagger:hilt-android:${VERSION_HILT}"
        const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${VERSION_HILT}"
    }

    object Test {
        const val JUNIT = "junit:junit:${VERSION_JUNIT}"
        const val JUNIT_ANDROID = "androidx.test.ext:junit:${VERSION_JUNIT_ANDROID}"
        const val ESPRESSO = "androidx.test.espresso:espresso-core:${VERSION_ESPRESSO}"
        const val COROUTINES_TEST = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${VERSION_COROUTINES_TEST}"
        const val TURBINE = "app.cash.turbine:turbine:${VERSION_TURBINE}"
    }

    object AndroidX {
        const val CORE = "androidx.core:core-ktx:${VERSION_ANDROIDX_CORE}"
        const val APP_COMPAT = "androidx.appcompat:appcompat:${VERSION_ANDROIDX_APPCOMPAT}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${VERSION_ANDROIDX_CONSTRAINT_LAYOUT}"

        // Navigation
        const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${VERSION_ANDROIDX_NAVIGATION}"
        const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${VERSION_ANDROIDX_NAVIGATION}"

        // RecyclerView (with ConcatAdapter)
        const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:${VERSION_RECYCLER_VIEW}"

        // CardView
        const val CARD_VIEW = "androidx.cardview:cardview:${VERSION_CARD_VIEW}"

        // Paging
        const val PAGING = "androidx.paging:paging-runtime:${VERSION_PAGING}"
        const val PAGING_COMMON = "androidx.paging:paging-common:${VERSION_PAGING}"
    }

    object Glide {
        const val GLIDE = "com.github.bumptech.glide:glide:${VERSION_GLIDE}"
        const val GLIDE_COMPILER = "com.github.bumptech.glide:compiler:${VERSION_GLIDE}"
    }

    object DotsIndicator {
        const val DOTS_INDICATOR = "com.tbuonomo:dotsindicator:${VERSION_DOTS_INDICATOR}"
    }

    object Retrofit {
        const val RETROFIT = "com.squareup.retrofit2:retrofit:${VERSION_RETROFIT}"
        const val RETROFIT_CONVERTOR = "com.squareup.retrofit2:converter-gson:${VERSION_RETROFIT}"
    }

    object OkHttp {
        const val OKHTTP = "com.squareup.okhttp3:okhttp:${VERSION_OKHTTP}"
        const val OKHTTP_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${VERSION_OKHTTP}"
    }

    object Gson {
        const val GSON = "com.google.code.gson:gson:${VERSION_GSON}"
    }

    object ShimmerEffect {
        const val SHIMMER_EFFECT = "com.facebook.shimmer:shimmer:${VERSION_SHIMMER_EFFECT}"
    }

}