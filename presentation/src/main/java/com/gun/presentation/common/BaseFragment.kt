package com.gun.presentation.common

import android.os.Bundle
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.fragment.app.Fragment
import com.gun.presentation.ui.main.MainActivity

abstract class BaseFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWindowLayoutNoLimit(false)
    }

    fun setWindowLayoutNoLimit(needExpand: Boolean) {
        (requireActivity() as MainActivity).setWindowLayoutNoLimit(needExpand)
    }

    fun setFadeAnimation(rootView: View, vararg viewIds: Int) {
        val anim = AlphaAnimation(0.0f, 1.0f)
        anim.duration = 600L

        for (viewId in viewIds) {
            rootView.findViewById<View>(viewId).startAnimation(anim)
        }
    }
}