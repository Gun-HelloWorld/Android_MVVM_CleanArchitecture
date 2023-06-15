package com.gun.presentation.ui.search

import com.gun.presentation.ui.common.BadResultType

sealed class SearchUiEvent {

    data class ShowBadResult(val badResultType: BadResultType) : SearchUiEvent()

    object HideBadResult : SearchUiEvent()

    object ShowLoading : SearchUiEvent()

    object HideLoading : SearchUiEvent()

}