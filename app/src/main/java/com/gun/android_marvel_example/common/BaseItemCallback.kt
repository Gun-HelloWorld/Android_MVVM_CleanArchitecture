package com.gun.android_marvel_example.common

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class BaseItemCallback<T : Any> : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem.toString() == newItem.toString()

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}
