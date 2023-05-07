package com.gun.data.di

import com.gun.data.datasource.*
import com.gun.data.network.MarvelApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideRemoteDataSource(marvelApi: MarvelApi): CharacterDataSource.Remote {
        return CharacterRemoteDataSource(marvelApi)
    }

    @Provides
    @Singleton
    fun provideComicDataSource(marvelApi: MarvelApi): ComicDataSource.Remote {
        return ComicRemoteDataSource(marvelApi)
    }

    @Provides
    @Singleton
    fun provideCreatorDataSource(marvelApi: MarvelApi): CreatorDataSource.Remote {
        return CreatorRemoteDataSource(marvelApi)
    }

    @Provides
    @Singleton
    fun provideEventDataSource(marvelApi: MarvelApi): EventDataSource.Remote {
        return EventRemoteDataSource(marvelApi)
    }

    @Provides
    @Singleton
    fun provideSeriesDataSource(marvelApi: MarvelApi): SeriesDataSource.Remote {
        return SeriesRemoteDataSource(marvelApi)
    }
}