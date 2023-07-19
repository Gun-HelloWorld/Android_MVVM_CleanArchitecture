package com.gun.presentation.ui.favorite

interface FavoriteChangedListener<T> {
    fun onFavoriteChange(data : T, isChecked: Boolean)
}