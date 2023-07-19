package com.gun.domain.model.mapper

import com.gun.domain.common.ContentType
import com.gun.domain.model.favorite.Favorite
import com.gun.domain.model.search.SearchResult

fun SearchResult.parseFavorite(contentType: ContentType): Favorite {
    return Favorite(
        id = id,
        name = name,
        thumbnailPath = thumbnailPath,
        thumbnailExtension = thumbnailExtension,
        contentType = contentType
    )
}