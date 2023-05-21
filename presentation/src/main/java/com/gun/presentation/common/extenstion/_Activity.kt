package com.gun.presentation.common.extenstion

import android.app.Activity
import android.os.Build
import android.view.WindowManager
import androidx.core.view.WindowCompat

fun Activity.setStatusBarTransparent() {
    window.apply {
        setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        )
    }

    if(Build.VERSION.SDK_INT >= 30) {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }
}

fun Activity.setStatusBarOrigin() {
    window.apply {
        clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }
    if(Build.VERSION.SDK_INT >= 30) {
        WindowCompat.setDecorFitsSystemWindows(window, true)
    }
}

fun Activity.getBottomSoftKeyHeight(): Int {
    var navigationBarHeight = 0

    val resId = resources.getIdentifier("navigation_bar_height", "dimen", "android")

    if (resId > 0) {
        navigationBarHeight = resources.getDimensionPixelSize(resId)
    }

    return navigationBarHeight
}