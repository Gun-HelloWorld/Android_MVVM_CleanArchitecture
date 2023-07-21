package com.gun.presentation.ui.common

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.view.doOnPreDraw
import com.gun.mvvm_cleanarchitecture.R
import com.gun.mvvm_cleanarchitecture.databinding.ViewCustomBadResultBinding

sealed class BadResultType
object ResultErrorType: BadResultType()
object ResultEmptyType: BadResultType()
object NoneType: BadResultType()

class CustomBadResultView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var binding: ViewCustomBadResultBinding

    init {
        if (!isInEditMode) {
            binding = ViewCustomBadResultBinding.inflate(LayoutInflater.from(context), this, false)
            addView(binding.root)
        }
    }

    fun show(badResultType: BadResultType) {
        with(binding) {
            doOnPreDraw {
                tvTitle.text = getTitleFromBadResultType(badResultType)
                tvMessage.text = getMessageFromBadResultType(badResultType)
                btnRetry.visibility = if (badResultType == ResultEmptyType) View.GONE else View.VISIBLE
                visibility = View.VISIBLE
            }
        }
    }

    fun hide() {
        visibility = View.GONE
    }

    fun setRetryClickListener(clickListener: OnClickListener) {
        binding.btnRetry.setOnClickListener(clickListener)
    }

    private fun getTitleFromBadResultType(badResultType: BadResultType) = when(badResultType) {
        is ResultErrorType -> context.getString(R.string.title_error_exception)
        is ResultEmptyType -> context.getString(R.string.title_error_result_not_found)
        is NoneType -> null
    }

    private fun getMessageFromBadResultType(badResultType: BadResultType) = when(badResultType) {
        is ResultErrorType -> context.getString(R.string.msg_error_exception)
        is ResultEmptyType -> context.getString(R.string.msg_error_result_not_found)
        is NoneType -> null
    }
}