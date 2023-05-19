package com.gun.domain.model


data class Series(
    val id: Int,
    val title: String,
    val description: String,
    val startYear: Int,
    val endYear: Int,
    val rating: String,
    val thumbnailPath: String,
    val thumbnailExtension: String,
    val detailUrl: String,
    val copyright: String,
    val attributionText: String,
    val creatorInfoList : List<SimpleInfo>,
    val characterInfoList : List<SimpleInfo>,
    val storyInfoList : List<SimpleInfo>,
    val comicInfoList : List<SimpleInfo>,
    val eventInfoList : List<SimpleInfo>,
)