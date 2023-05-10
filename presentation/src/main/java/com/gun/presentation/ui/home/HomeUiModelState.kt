package com.gun.presentation.ui.home

import com.gun.presentation.ui.home.model.HomeUiModel

sealed class HomeUiModelState {
    object Nothing : HomeUiModelState()

    data class ShowData(val data: HomeUiModel) : HomeUiModelState()
}