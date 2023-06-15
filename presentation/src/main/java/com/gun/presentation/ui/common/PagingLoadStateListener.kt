package com.gun.presentation.ui.common

import androidx.paging.CombinedLoadStates

interface PagingLoadStateListener {
    fun onLoad(loadState: CombinedLoadStates, adapterItemCount: Int)
}