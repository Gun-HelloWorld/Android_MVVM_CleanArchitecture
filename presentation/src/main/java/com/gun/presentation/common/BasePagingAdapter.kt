package com.gun.presentation.common

import androidx.paging.PagingDataAdapter

abstract class BasePagingAdapter<T : Any, VH : BaseViewHolder>(
    var itemClickListener: ItemClickListener<T>? = null
) : PagingDataAdapter<T, VH>(BaseItemCallback())

