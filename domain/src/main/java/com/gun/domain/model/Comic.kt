package com.gun.domain.model

data class Comic(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnailPath: String,
    val thumbnailExtension: String,
    val copyright: String,
    val attributionText: String
)