package com.gun.data.mapper

import com.gun.data.entity.series.SeriesDto
import com.gun.domain.model.Series
import com.gun.domain.model.SimpleInfo


//SeriesModel : copyright, attributionText, id, title, description, startYear, endYear, rating, thumbnailPath, thumbnailExtension
//List<Creator>
//List<Character>
//List<Story>
//List<Comic>
//List<Event>
//detailUrl
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