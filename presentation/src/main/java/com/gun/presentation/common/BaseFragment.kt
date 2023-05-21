package com.gun.presentation.common

import androidx.fragment.app.Fragment
import com.gun.presentation.ui.main.MainActivity

abstract class BaseFragment: Fragment() {

    fun setWindowLayoutNoLimit(needExpand: Boolean) {
        (requireActivity() as MainActivity).setWindowLayoutNoLimit(needExpand)
    }

}