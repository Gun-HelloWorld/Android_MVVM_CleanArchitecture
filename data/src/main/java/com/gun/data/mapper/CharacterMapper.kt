package com.gun.data.mapper

import com.gun.data.entity.response.remote.character.CharactersDto
import com.gun.domain.model.Character
import com.gun.domain.model.SimpleInfo
import com.gun.domain.model.search.PagingModel
import com.gun.domain.model.search.SearchResult

fun CharactersDto.toDomainModel(): List<Character> {
    return data.results.map { result ->
        Character(
            id = result.id,
            name = result.name,
            description = result.description ?: "",
            thumbnailPath = result.thumbnail.path,
            thumbnailExtension = result.thumbnail.extension,
            detailUrl = result.getDetailUrl(),
            copyright = copyright,
            attributionText = attributionText,
            comicInfoList = result.comics.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            seriesInfoList = result.series.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            storyInfoList = result.stories.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            eventInfoList = result.stories.items.map { SimpleInfo(it.resourceURI, it.name, "") }
        )
    }
}

fun CharactersDto.toPagingModelOfSearch(): PagingModel<SearchResult> {
    val searchResultList = data.results.map { result ->
        SearchResult(
            id = result.id,
            name = result.name,
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