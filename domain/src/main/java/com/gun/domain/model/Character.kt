package com.gun.domain.model

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnailPath: String,
    val thumbnailExtension: String,
    val copyright: String,
    val attributionText: String
)