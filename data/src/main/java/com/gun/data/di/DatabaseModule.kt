package com.gun.data.di

import android.content.Context
import androidx.room.Room
import com.gun.data.database.MarvelDatabase
import com.gun.data.database.MarvelDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDataBase(@ApplicationContext appContext: Context) : MarvelDatabase {
        return Room.databaseBuilder(
            appContext,
            MarvelDatabase::class.java,
            MarvelDatabase.DATABASE_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun providesDao(database: MarvelDatabase) : MarvelDao {
        return database.getFavoriteDao()
    }

}