package com.gun.presentation.common

import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<T : Any, VH : BaseViewHolder> :
    ListAdapter<T, VH>(BaseItemCallback<T>()) {
}

