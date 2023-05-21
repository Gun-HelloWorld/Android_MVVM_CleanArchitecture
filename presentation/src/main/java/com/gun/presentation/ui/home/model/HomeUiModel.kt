package com.gun.presentation.ui.home.model

import com.gun.domain.common.ContentType

data class HomeUiModel(
    val homeUiSubModelList: List<HomeUiSubModel>
) {
    fun fromUiModelType(contentType: ContentType): HomeUiSubModel {
        return homeUiSubModelList.first { it.contentType == contentType }
    }
}