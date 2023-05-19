package com.gun.data.mapper

import com.gun.data.entity.creator.CreatorDto
import com.gun.domain.model.Creator
import com.gun.domain.model.SimpleInfo

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