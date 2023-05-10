package com.gun.presentation.ui.home.model

data class HomeUiSubModel(
    val homeUiModelType: HomeUiModelType,
    var homeListItem: List<HomeListItem>?,
) {
    fun filterThumbnailAvailable() = HomeUiSubModel(
            homeUiModelType,
            homeListItem?.filter { it.isThumbnailAvailable() }
    )

    fun sliceHomeListItem(count: Int): List<HomeListItem>? {
        if (homeListItem.isNullOrEmpty() || count > homeListItem!!.size) {
            return homeListItem
        }

        return homeListItem!!.slice(0 until 5)
    }
}