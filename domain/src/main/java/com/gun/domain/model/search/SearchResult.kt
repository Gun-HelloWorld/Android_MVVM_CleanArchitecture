package com.gun.domain.model.search

import com.gun.domain.util.DateParser

private const val SEARCH_ITEM_THUMBNAIL_IMAGE_SIZE = "landscape_xlarge"

data class SearchResult(
    val id: Int,
    val name: String,
    val thumbnailPath: String,
    val thumbnailExtension: String,
    val modified: String?
) {
    fun getSearchItemThumbnailUrl(): String {
        return "${thumbnailPath}/$SEARCH_ITEM_THUMBNAIL_IMAGE_SIZE.${thumbnailExtension}"
    }

    fun getModifiedByDisplayFormat(): String {
        return DateParser.parseToDateFormatString(modified, DateParser.SEARCH_DATE_FORMAT) ?: "Unknown"
    }
}