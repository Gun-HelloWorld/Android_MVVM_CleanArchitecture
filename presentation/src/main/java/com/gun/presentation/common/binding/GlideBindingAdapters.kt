package com.gun.presentation.common.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class GlideAppModule : AppGlideModule()

@BindingAdapter("imageUrl", "imageError")
fun loadImage(imageView: ImageView, url: String, error: Drawable) {
    Glide.with(imageView.context)
        .load(url)
        .sizeMultiplier(0.5f)
        .error(error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)
}
