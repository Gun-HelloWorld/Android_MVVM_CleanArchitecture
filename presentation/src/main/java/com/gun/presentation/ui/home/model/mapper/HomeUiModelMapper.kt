package com.gun.presentation.ui.home.model.mapper

import com.gun.domain.common.*
import com.gun.domain.common.Constants.TYPE_CHARACTER
import com.gun.domain.common.Constants.TYPE_COMIC
import com.gun.domain.common.Constants.TYPE_CREATOR
import com.gun.domain.common.Constants.TYPE_EVENT
import com.gun.domain.common.Constants.TYPE_SERIES
import com.gun.domain.model.home.HomeList
import com.gun.presentation.ui.home.model.HomeListItem
import com.gun.presentation.ui.home.model.HomeUiModel
import com.gun.presentation.ui.home.model.HomeUiSubModel

fun HomeList.toUiModel(): HomeUiModel {
    val parsedCharacterList = characterList?.map {
        HomeListItem(it.id, it.name, it.thumbnailPath, it.thumbnailExtension, TYPE_CHARACTER)
    }?.sortedWith(HomeListItem.thumbnailAvailableComparator())

    val parsedComicList = comicList?.map {
        HomeListItem(it.id, it.title, it.thumbnailPath, it.thumbnailExtension, TYPE_COMIC)
    }?.sortedWith(HomeListItem.thumbnailAvailableComparator())

    val parsedCreatorList = creatorList?.map {
        HomeListItem(it.id, it.fullName, it.thumbnailPath, it.thumbnailExtension, TYPE_CREATOR)
    }?.sortedWith(HomeListItem.thumbnailAvailableComparator())

    val parsedEventList = eventList?.map {
        HomeListItem(it.id, it.title, it.thumbnailPath, it.thumbnailExtension, TYPE_EVENT)
    }?.sortedWith(HomeListItem.thumbnailAvailableComparator())

    val parsedSeriesList = seriesList?.map {
        HomeListItem(it.id, it.title, it.thumbnailPath, it.thumbnailExtension, TYPE_SERIES)
    }?.sortedWith(HomeListItem.thumbnailAvailableComparator())

    return HomeUiModel(
        mutableListOf(
            HomeUiSubModel(contentType = CharacterType, homeListItem = parsedCharacterList),
            HomeUiSubModel(contentType = ComicType, homeListItem = parsedComicList),
            HomeUiSubModel(contentType = CreatorType, homeListItem = parsedCreatorList),
            HomeUiSubModel(contentType = EventType, homeListItem = parsedEventList),
            HomeUiSubModel(contentType = SeriesType, homeListItem = parsedSeriesList),
        )
    )
}