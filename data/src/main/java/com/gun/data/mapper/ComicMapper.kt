package com.gun.data.mapper

import com.gun.data.entity.response.remote.comic.ComicDto
import com.gun.domain.model.Comic
import com.gun.domain.model.SimpleInfo
import com.gun.domain.model.search.PagingModel
import com.gun.domain.model.search.SearchResult

fun ComicDto.toDomainModel(): List<Comic> {
    return data.results.map { result ->
        Comic(
            id = result.id,
            title = result.title,
            description = result.getAvailableDescription(),
            format = result.format,
            thumbnailPath = result.thumbnail.path,
            thumbnailExtension = result.thumbnail.extension,
            detailUrl = result.getDetailUrl(),
            copyright = copyright,
            attributionText = attributionText,
            seriesInfoList = mutableListOf(SimpleInfo(result.series.resourceURI, result.series.name, "")),
            creatorInfoList = result.creators.items.map { SimpleInfo(it.resourceURI, it.name, it.role) },
            characterInfoList = result.characters.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            storyInfoList = result.stories.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            eventInfoList = result.stories.items.map { SimpleInfo(it.resourceURI, it.name, "") }
        )
    }
}

fun ComicDto.toPagingModelOfSearch(): PagingModel<SearchResult> {
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