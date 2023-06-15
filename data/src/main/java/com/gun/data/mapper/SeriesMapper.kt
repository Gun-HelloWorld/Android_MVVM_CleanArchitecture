package com.gun.data.mapper

import com.gun.data.entity.series.SeriesDto
import com.gun.domain.model.Series
import com.gun.domain.model.SimpleInfo
import com.gun.domain.model.search.PagingModel
import com.gun.domain.model.search.SearchResult

fun SeriesDto.toDomainModel(): List<Series> {
    return data.results.map { result ->
        Series(
            id = result.id,
            title = result.title,
            description = result.description ?: "",
            startYear = result.startYear,
            endYear = result.endYear,
            rating = result.rating,
            thumbnailPath = result.thumbnail.path,
            thumbnailExtension = result.thumbnail.extension,
            detailUrl = result.getDetailUrl(),
            copyright = copyright,
            attributionText = attributionText,
            creatorInfoList = result.creators.items.map { SimpleInfo(it.resourceURI, it.name, it.role) },
            characterInfoList = result.characters.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            storyInfoList = result.stories.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            comicInfoList = result.comics.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            eventInfoList = result.events.items.map { SimpleInfo(it.resourceURI, it.name, "") },
        )
    }
}

fun SeriesDto.toPagingModelOfSearch(): PagingModel<SearchResult> {
    val searchResultList = data.results.map { result ->
        SearchResult(
            id = result.id,
            name = result.title,
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