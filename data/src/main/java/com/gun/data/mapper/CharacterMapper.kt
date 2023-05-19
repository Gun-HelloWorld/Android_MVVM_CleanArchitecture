package com.gun.data.mapper

import com.gun.data.entity.character.CharactersDto
import com.gun.domain.model.Character
import com.gun.domain.model.SimpleInfo

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