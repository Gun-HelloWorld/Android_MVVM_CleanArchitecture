package com.gun.presentation.ui.favorite.model

import com.gun.domain.common.*

sealed class FavoriteUiFilterState {
    object All: FavoriteUiFilterState()
    object Series: FavoriteUiFilterState()
    object Comic: FavoriteUiFilterState()
    object Event: FavoriteUiFilterState()
    object Character: FavoriteUiFilterState()
    object Creator: FavoriteUiFilterState()

    fun name(): String {
        return this.javaClass.simpleName
    }

    fun parseToContentType(): ContentType? = when (this) {
            is All -> null
            is Series -> SeriesType
            is Comic -> ComicType
            is Event -> EventType
            is Character -> CharacterType
            is Creator -> CreatorType
    }
}

