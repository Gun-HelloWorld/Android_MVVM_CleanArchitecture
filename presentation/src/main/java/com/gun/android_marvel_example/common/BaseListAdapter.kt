package com.gun.android_marvel_example.common

import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<T : Any, VH : BaseViewHolder> :
    ListAdapter<T, VH>(BaseItemCallback<T>()) {
}

