package com.gun.data.mapper

import com.gun.data.entity.event.EventDto
import com.gun.domain.model.Event
import com.gun.domain.model.SimpleInfo

fun EventDto.toDomainModel(): List<Event> {
    return data.results.map { result ->
        Event(
            id = result.id,
            title = result.title,
            description = result.description ?: "",
            start = result.start ?: "",
            end = result.end ?: "",
            thumbnailPath = result.thumbnail.path,
            thumbnailExtension = result.thumbnail.extension,
            detailUrl = result.getDetailUrl(),
            copyright = copyright,
            attributionText = attributionText,
            creatorInfoList = result.creators.items.map { SimpleInfo(it.resourceURI, it.name, it.role) },
            characterInfoList = result.characters.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            storyInfoList = result.stories.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            comicInfoList = result.comics.items.map { SimpleInfo(it.resourceURI, it.name, "") },
            seriesInfoList = result.comics.items.map { SimpleInfo(it.resourceURI, it.name, "") },
        )
    }
}