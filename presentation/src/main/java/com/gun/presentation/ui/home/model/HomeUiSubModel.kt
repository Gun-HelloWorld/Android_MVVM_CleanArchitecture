package com.gun.presentation.ui.home.model

import com.gun.domain.common.ContentType

data class HomeUiSubModel(
    val contentType: ContentType,
    var homeListItem: List<HomeListItem>?,
) {
    fun filterThumbnailAvailable() = HomeUiSubModel(
            contentType,
            homeListItem?.filter { it.isThumbnailAvailable() }
    )

    fun sliceHomeListItem(count: Int): List<HomeListItem>? {
        if (homeListItem.isNullOrEmpty() || count > homeListItem!!.size) {
            return homeListItem
        }

        return homeListItem!!.slice(0 until 5)
    }
}