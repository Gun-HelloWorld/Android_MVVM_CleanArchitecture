package com.gun.domain.model.detail

import com.gun.domain.model.SimpleInfo

private const val DETAIL_THUMBNAIL_IMAGE_SIZE = "portrait_xlarge"

data class ContentDetail(
    val id: Int,
    val name: String,
    val description: String,
    val format: String,
    val thumbnailPath: String,
    val thumbnailExtension: String,
    val detailUrl: String,
    val copyright: String,
    val attributionText: String,
    val characterInfoList: List<SimpleInfo>,
    val comicInfoList: List<SimpleInfo>,
    val seriesInfoList: List<SimpleInfo>,
    val storyInfoList: List<SimpleInfo>,
    val eventInfoList: List<SimpleInfo>,
    val creatorInfoList: List<SimpleInfo>
) {
    fun getThumbnailUrl(): String {
        return "${thumbnailPath}/$DETAIL_THUMBNAIL_IMAGE_SIZE.${thumbnailExtension}"
    }

    fun joinStringCharacters() = characterInfoList.joinToString(", ") {
        it.name
    }

    fun joinStringComics() = comicInfoList.joinToString(", ") {
        it.name
    }

    fun joinStringSeries() = seriesInfoList.joinToString(", ") {
        it.name
    }

    fun joinStringStories() = storyInfoList.joinToString(", ") {
        it.name
    }

    fun joinStringEvent() = eventInfoList.joinToString(", ") {
        it.name
    }

    fun joinStringCreator() = creatorInfoList.joinToString(", ") {
        it.name
    }
}