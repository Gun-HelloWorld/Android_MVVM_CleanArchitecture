package com.gun.presentation.common.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import android.widget.ImageView
import androidx.renderscript.Allocation
import androidx.renderscript.Element
import androidx.renderscript.RenderScript
import androidx.renderscript.ScriptIntrinsicBlur

object BlurUtil {
    // RenderEffect Blur Radius 최대값은 Float 범위에 해당하지만, RenderScript Blur Radius 최대값은 25f 이므로,
    // 임의로 RenderEffect Blur Radius 최대값을 RenderScript Blur Radius 최대값과 동일하게 보이는 160f 로 설정
    private const val MAX_BLUR_FOR_RENDER_EFFECT = 160f
    private const val MAX_BLUR_FOR_RENDER_SCRIPT = 25f

    fun blurToDetailBackground(
        context: Context?,
        imageView: ImageView,
        bitmap: Bitmap,
        @androidx.annotation.IntRange(from = 0, to = 100) blurPercent: Int = 0
    ) {
        if (blurPercent == 0) return

        val radius = calculateBlurRadius(blurPercent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val renderEffect = RenderEffect.createBlurEffect(radius, radius, Shader.TileMode.DECAL)

            imageView.setRenderEffect(renderEffect)
        } else {
            val rs: RenderScript = RenderScript.create(context)
            val intrinsicBlur: ScriptIntrinsicBlur =
                ScriptIntrinsicBlur.create(rs, Element.U8_4(rs))
            val tmpIn: Allocation = Allocation.createFromBitmap(rs, bitmap)
            val tmpOut: Allocation = Allocation.createFromBitmap(rs, bitmap)
            intrinsicBlur.setRadius(radius)
            intrinsicBlur.setInput(tmpIn)
            intrinsicBlur.forEach(tmpOut)
            tmpOut.copyTo(bitmap)

            imageView.setImageBitmap(bitmap)
        }
    }

    private fun calculateBlurRadius(
        @androidx.annotation.IntRange(from = 0, to = 100) blurPercent: Int = 0
    ): Float {
        val radius = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MAX_BLUR_FOR_RENDER_EFFECT
        } else {
            MAX_BLUR_FOR_RENDER_SCRIPT
        }

        return blurPercent / 100f *  radius
    }
}
