package com.gun.data.mapper

import com.gun.data.entity.event.EventDto
import com.gun.domain.model.Event

fun EventDto.toDomainModel(): List<Event> {
    return data.results.map {
        Event(
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