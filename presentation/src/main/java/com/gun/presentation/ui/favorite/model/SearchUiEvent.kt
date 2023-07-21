package com.gun.presentation.ui.favorite.model

import com.gun.presentation.ui.common.BadResultType

sealed class FavoriteUiEvent {

    data class ShowBadResult(val badResultType: BadResultType) : FavoriteUiEvent()

    object HideBadResult : FavoriteUiEvent()

    object ShowLoading : FavoriteUiEvent()

    object HideLoading : FavoriteUiEvent()

}