package com.gun.data.datasource.local

import com.gun.data.entity.response.local.FavoriteEntity
import com.gun.domain.common.ContentType

interface MarvelLocalDataSource {
    suspend fun getFavoriteList(contentType: ContentType?): Result<List<FavoriteEntity>>

    suspend fun insertFavorite(favoriteEntity: FavoriteEntity): Result<FavoriteEntity>

    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity): Result<FavoriteEntity>
}