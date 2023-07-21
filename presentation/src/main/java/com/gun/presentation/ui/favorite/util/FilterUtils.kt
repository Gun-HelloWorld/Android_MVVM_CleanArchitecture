package com.gun.presentation.ui.favorite.util

import android.content.res.Resources
import com.gun.mvvm_cleanarchitecture.R
import com.gun.presentation.ui.favorite.model.FavoriteUiFilterState
import com.gun.presentation.ui.favorite.model.FilterItem

object FilterUtils {

    fun getFilterItemList(resources: Resources, favoriteFilter: FavoriteUiFilterState): List<FilterItem> {
        return resources.getStringArray(R.array.favorite_filter_items)
            .toList()
            .map {
                val isSelectedItem = it == favoriteFilter.name()
                FilterItem(it, isSelectedItem, parseToFilterType(resources, it))
            }
    }

    fun parseToFilterType(resources: Resources, str: String) = when (str) {
        resources.getString(R.string.label_all) -> FavoriteUiFilterState.All
        resources.getString(R.string.label_series) -> FavoriteUiFilterState.Series
        resources.getString(R.string.label_comic) -> FavoriteUiFilterState.Comic
        resources.getString(R.string.label_event) -> FavoriteUiFilterState.Event
        resources.getString(R.string.label_character) -> FavoriteUiFilterState.Character
        resources.getString(R.string.label_creator) -> FavoriteUiFilterState.Creator
        else -> throw IllegalArgumentException()
    }

}