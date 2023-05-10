package com.gun.presentation.ui.home

import com.gun.presentation.ui.home.model.HomeUiModel

sealed class HomeUiState {
    data class ShowLoading(val loadingCount: Int) : HomeUiState()
    data class ShowData(val data: HomeUiModel) : HomeUiState()
    data class ShowMessage(val message: String) : HomeUiState()
}