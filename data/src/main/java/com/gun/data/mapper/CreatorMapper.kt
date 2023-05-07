package com.gun.data.mapper

import com.gun.data.entity.creator.CreatorDto
import com.gun.domain.model.Creator

fun CreatorDto.toDomainModel(): List<Creator> {
    return data.results.map {
        Creator(
            id = it.id,
            fullName = it.fullName,
            thumbnailPath = it.thumbnail.path,
            thumbnailExtension = it.thumbnail.extension,
        )
    }
}