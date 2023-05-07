package com.gun.domain.model


data class Series(
    val id: Int,
    val title: String,
    val description: String,
    val startYear: Int,
    val endYear: Int,
    val rating: String,
    val type: String,
    val thumbnailPath: String,
    val thumbnailExtension: String,
    val copyright: String,
    val attributionText: String
): java.io.Serializable