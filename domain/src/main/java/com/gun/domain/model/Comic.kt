package com.gun.domain.model

data class Comic(
    val id: Int,
    val title: String,
    val description: String,
    val format : String,
    val thumbnailPath: String,
    val thumbnailExtension: String,
    val detailUrl: String,
    val copyright: String,
    val attributionText: String,
    val seriesInfoList : List<SimpleInfo>,
    val creatorInfoList : List<SimpleInfo>,
    val characterInfoList : List<SimpleInfo>,
    val storyInfoList : List<SimpleInfo>,
    val eventInfoList : List<SimpleInfo>
)