package com.gun.domain.model.favorite

import com.gun.domain.common.*

data class Favorite(
    val id: Int,
    val name: String,
    val thumbnailPath: String,
    val thumbnailExtension: String,
    val contentType: ContentType
)