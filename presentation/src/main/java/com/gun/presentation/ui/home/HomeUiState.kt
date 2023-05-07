package com.gun.presentation.ui.home

import com.gun.domain.model.HomeList

sealed class HomeUiState {
    data class ShowLoading(val loadingCount: Int) : HomeUiState()
    data class ShowData(val data: HomeList) : HomeUiState()
    data class ShowMessage(val message: String) : HomeUiState()
}