package com.gun.data.mapper

import com.gun.data.entity.response.local.FavoriteEntity
import com.gun.domain.common.name
import com.gun.domain.common.parseToContentType
import com.gun.domain.model.favorite.Favorite

fun FavoriteEntity.toDomainModel(): Favorite {
    return Favorite(
        id = id,
        name = name,
        thumbnailPath = thumbnailPath,
        thumbnailExtension = thumbnailExtension,
        contentType = contentType.parseToContentType()
    )
}

fun Favorite.toEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = id,
        name = name,
        thumbnailPath = thumbnailPath,
        thumbnailExtension = thumbnailExtension,
        contentType = contentType.name()
    )
}