package com.gun.data.entity.response.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class FavoriteEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "thumbnail_path") val thumbnailPath: String,
    @ColumnInfo(name = "thumbnail_extension") val thumbnailExtension: String,
    @ColumnInfo(name = "content_type") val contentType: String
)