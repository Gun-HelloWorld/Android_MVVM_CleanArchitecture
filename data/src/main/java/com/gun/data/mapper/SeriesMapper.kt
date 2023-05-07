package com.gun.data.mapper

import com.gun.data.entity.series.SeriesDto
import com.gun.domain.model.Series

fun SeriesDto.toDomainModel(): List<Series> {
    return data.results.map {
        Series(
            id = it.id,
            title = it.title,
            description = it.description ?: "",
            startYear = it.startYear,
            endYear = it.endYear,
            rating = it.rating,
            type = it.type,
            thumbnailPath = it.thumbnail.path,
            thumbnailExtension = it.thumbnail.extension,
            copyright = copyright,
            attributionText = attributionText
        )
    }
}