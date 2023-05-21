package com.gun.domain.model

data class Event(
    val id: Int,
    val title: String,
    val start : String,
    val end : String,
    val description: String,
    val thumbnailPath: String,
    val thumbnailExtension: String,
    val detailUrl: String,
    val copyright: String,
    val attributionText: String,
    val creatorInfoList : List<SimpleInfo>,
    val characterInfoList : List<SimpleInfo>,
    val storyInfoList : List<SimpleInfo>,
    val comicInfoList : List<SimpleInfo>,
    val seriesInfoList : List<SimpleInfo>,
)