package com.gun.presentation.ui.home

import com.gun.presentation.ui.home.model.HomeListItem

interface ItemClickListener {
    fun onClickItem(data : HomeListItem)
}