package com.gun.presentation.common.binding

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.module.AppGlideModule
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.gun.presentation.common.ImageLoadListener

@GlideModule
class GlideAppModule : AppGlideModule()

@BindingAdapter(value = ["imageUrl", "imageError", "imageListener"], requireAll = false)
fun loadImage(imageView: ImageView, url: String?, error: Drawable, listener: ImageLoadListener?) {
    if (url.isNullOrEmpty()) return

    if (listener == null) {
        loadImageWithoutListener(imageView, url, error)
    } else {
        loadImageWithListener(imageView, url, error, listener)
    }
}

private fun loadImageWithoutListener(imageView: ImageView, url: String?, error: Drawable) {
    Glide.with(imageView.context)
        .load(url)
        .sizeMultiplier(0.5f)
        .error(error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(imageView)
}

private fun loadImageWithListener(imageView: ImageView, url: String?, error: Drawable, listener: ImageLoadListener) {
    Glide.with(imageView.context)
        .asBitmap()
        .load(url)
        .sizeMultiplier(0.5f)
        .error(error)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                imageView.setImageBitmap(resource)
                listener.onImageLoadedWithBitmap(imageView, resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {

            }
        })
}
