package com.gun.presentation.ui.search

import com.gun.domain.common.ContentType

sealed class SearchPageMoveEvent {

    data class MoveToDetail(val contentId: Int, val contentType: ContentType) : SearchPageMoveEvent()

}