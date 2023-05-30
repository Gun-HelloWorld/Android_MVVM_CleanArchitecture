package com.gun.presentation.common

import android.graphics.Bitmap
import android.widget.ImageView

interface ImageLoadListener {
    fun onImageLoadedWithBitmap(imageView: ImageView, bitmap: Bitmap)
}