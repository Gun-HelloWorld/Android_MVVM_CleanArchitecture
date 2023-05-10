package com.gun.presentation.ui.home.model.mapper

import com.gun.domain.common.Constants.TYPE_CHARACTER
import com.gun.domain.common.Constants.TYPE_COMIC
import com.gun.domain.common.Constants.TYPE_CREATOR
import com.gun.domain.common.Constants.TYPE_EVENT
import com.gun.domain.common.Constants.TYPE_SERIES
import com.gun.domain.model.HomeList
import com.gun.presentation.ui.home.model.*

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
            HomeUiSubModel(homeUiModelType = CharacterType, homeListItem = parsedCharacterList),
            HomeUiSubModel(homeUiModelType = ComicType, homeListItem = parsedComicList),
            HomeUiSubModel(homeUiModelType = CreatorType, homeListItem = parsedCreatorList),
            HomeUiSubModel(homeUiModelType = EventType, homeListItem = parsedEventList),
            HomeUiSubModel(homeUiModelType = SeriesType, homeListItem = parsedSeriesList),
        )
    )
}