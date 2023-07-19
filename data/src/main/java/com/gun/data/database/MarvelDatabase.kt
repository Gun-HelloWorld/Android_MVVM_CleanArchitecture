package com.gun.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.gun.data.entity.response.local.FavoriteEntity

@Database(entities = [FavoriteEntity::class], version = 1)
abstract class MarvelDatabase: RoomDatabase() {
    abstract fun getFavoriteDao(): MarvelDao

    companion object {
        const val DATABASE_NAME = "marvel_favorite.db"
    }
}