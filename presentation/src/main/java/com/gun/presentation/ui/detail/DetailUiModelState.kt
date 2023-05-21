package com.gun.presentation.ui.detail

import com.gun.domain.model.detail.ContentDetail

sealed class DetailUiModelState {

    object Nothing : DetailUiModelState()

    data class ShowData(val data: ContentDetail) : DetailUiModelState()

}