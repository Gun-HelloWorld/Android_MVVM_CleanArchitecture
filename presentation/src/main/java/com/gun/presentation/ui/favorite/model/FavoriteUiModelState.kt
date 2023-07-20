package com.gun.presentation.ui.favorite.model

import com.gun.domain.model.favorite.Favorite

sealed class FavoriteUiModelState {
    object Nothing : FavoriteUiModelState()

    data class ShowData(val data: List<Favorite>) : FavoriteUiModelState()
}