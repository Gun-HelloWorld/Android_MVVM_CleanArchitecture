package com.gun.data.mapper

import com.gun.data.entity.comic.ComicDto
import com.gun.domain.model.Comic

fun ComicDto.toDomainModel(): List<Comic> {
    return data.results.map {
        Comic(
            id = it.id,
            title = it.title,
            description = it.description ?: "",
            thumbnailPath = it.thumbnail.path,
            thumbnailExtension = it.thumbnail.extension,
            copyright = copyright,
            attributionText = attributionText
        )
    }
}