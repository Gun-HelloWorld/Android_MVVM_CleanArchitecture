package com.gun.presentation.ui.favorite.model

import com.gun.presentation.common.BottomSheetItem

data class FilterItem(
    override val name: String,
    override var isSelected: Boolean,
    val favoriteUiFilterState: FavoriteUiFilterState
) : BottomSheetItem()