package com.gun.domain.model.favorite

import com.gun.domain.common.*

private const val HOME_LIST_ITEM_IMAGE_SIZE = "portrait_xlarge"

data class Favorite(
    val id: Int,
    val name: String,
    val thumbnailPath: String,
    val thumbnailExtension: String,
    val contentType: ContentType
) {

    fun getListItemThumbnailUrl(): String {
        return "${thumbnailPath}/${HOME_LIST_ITEM_IMAGE_SIZE}.${thumbnailExtension}"
    }

}