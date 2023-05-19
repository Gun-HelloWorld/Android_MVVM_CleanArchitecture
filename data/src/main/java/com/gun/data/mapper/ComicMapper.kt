package com.gun.data.mapper

import com.gun.data.entity.comic.ComicDto
import com.gun.domain.model.Comic
import com.gun.domain.model.SimpleInfo

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