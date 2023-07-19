package com.gun.data.database

import androidx.room.*
import com.gun.data.entity.response.local.FavoriteEntity

@Dao
interface MarvelDao {

    @Query("SELECT * FROM favorite_table")
    suspend fun getFavoriteList(): List<FavoriteEntity>

    @Query("SELECT * FROM favorite_table WHERE content_type LIKE :contentType")
    suspend fun getFavoriteListByContentType(contentType: String): List<FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favoriteEntity: FavoriteEntity): Long // return rowId (-1 is error)

    @Delete
    suspend fun deleteFavorite(favoriteEntity: FavoriteEntity): Int // return success row count (0 is error)

}