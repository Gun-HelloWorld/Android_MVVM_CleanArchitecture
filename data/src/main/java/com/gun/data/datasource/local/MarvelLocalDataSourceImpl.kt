package com.gun.data.datasource.local

import com.gun.data.database.MarvelDao
import com.gun.data.entity.response.local.FavoriteEntity
import com.gun.domain.common.ContentType
import com.gun.domain.common.name

class MarvelLocalDataSourceImpl(private val marvelDao: MarvelDao): MarvelLocalDataSource {
    override suspend fun getFavoriteList(contentType: ContentType?): Result<List<FavoriteEntity>> = try {
        val result = if (contentType == null ) {
            marvelDao.getFavoriteList()
        } else {
            marvelDao.getFavoriteListByContentType(contentType.name())
        }

        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun insertFavorite(favoriteEntity: FavoriteEntity): Result<FavoriteEntity> = try {
        val result = marvelDao.insertFavorite(favoriteEntity)

        if (result >= 0L) {
            Result.success(favoriteEntity)
        } else {
            Result.failure(IllegalArgumentException())
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun deleteFavorite(favoriteEntity: FavoriteEntity): Result<FavoriteEntity> = try {
        val result = marvelDao.deleteFavorite(favoriteEntity)

        if (result > 0) {
            Result.success(favoriteEntity)
        } else {
            Result.failure(IllegalArgumentException())
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}