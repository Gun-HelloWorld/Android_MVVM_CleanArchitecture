package com.gun.presentation.test

import androidx.recyclerview.widget.DiffUtil

class TestDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean {
        return oldItem == newItem
    }
}