package com.gun.presentation.ui.home.model

data class HomeUiModel(
    val homeUiSubModelList: List<HomeUiSubModel>
) {
    fun fromUiModelType(uiModelType: HomeUiModelType): HomeUiSubModel {
        return homeUiSubModelList.first { it.homeUiModelType == uiModelType }
    }
}