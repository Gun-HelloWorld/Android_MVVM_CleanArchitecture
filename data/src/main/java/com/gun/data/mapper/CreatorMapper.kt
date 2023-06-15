package com.gun.data.mapper

import com.gun.data.entity.creator.CreatorDto
import com.gun.domain.model.Creator
import com.gun.domain.model.SimpleInfo
import com.gun.domain.model.search.PagingModel
import com.gun.domain.model.search.SearchResult

fun CreatorDto.toDomainModel(): List<Creator> {
    return data.results.map { result ->
        Creator(
            id = result.id,
            fullName = result.fullName,
            thumbnailPath = result.thumbnail.path,
            thumbnailExtension = result.thumbnail.extension,
            detailUrl = result.getDetailUrl(),
            copyright = copyright,
            attributionText = attributionText,
            comicInfoList = result.comics.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            seriesInfoList = result.series.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            storyInfoList = result.stories.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            eventInfoList = result.events.items.map { SimpleInfo(it.resourceURI, it.name, "") },
        )
    }
}

fun CreatorDto.toPagingModelOfSearch(): PagingModel<SearchResult> {
    val searchResultList = data.results.map { result ->
        SearchResult(
            id = result.id,
            name = result.fullName,
            thumbnailPath = result.thumbnail.path,
            thumbnailExtension = result.thumbnail.extension,
            modified = result.modified
        )
    }

    return with(data) {
        PagingModel(
            offset = offset,
            limit = limit,
            total = total,
            count = count,
            list = searchResultList
        )
    }
}