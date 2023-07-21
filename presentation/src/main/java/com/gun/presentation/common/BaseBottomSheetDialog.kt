package com.gun.presentation.common

import android.app.Activity
import com.google.android.material.bottomsheet.BottomSheetDialog

abstract class BottomSheetItem {
    abstract var isSelected: Boolean
    abstract val name: String
}

abstract class BaseBottomSheetDialog<T : BottomSheetItem>(
    activity: Activity,
) : BottomSheetDialog(activity) {

    abstract fun submitData(dataList: List<T>): BaseBottomSheetDialog<T>

}