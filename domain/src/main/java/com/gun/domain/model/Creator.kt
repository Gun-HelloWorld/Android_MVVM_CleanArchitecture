package com.gun.domain.model

data class Creator(
    val id: Int,
    val fullName: String,
    val thumbnailPath: String,
    val thumbnailExtension: String,
    val detailUrl: String,
    val copyright: String,
    val attributionText: String,
    val comicInfoList : List<SimpleInfo>,
    val seriesInfoList : List<SimpleInfo>,
    val storyInfoList : List<SimpleInfo>,
    val eventInfoList : List<SimpleInfo>
)