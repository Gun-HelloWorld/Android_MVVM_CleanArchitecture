package com.gun.data.di

import com.gun.data.datasource.*
import com.gun.data.repository.*
import com.gun.domain.repository.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(characterDataSource: CharacterDataSource.Remote): CharacterRepository {
        return CharacterRepositoryImpl(characterDataSource)
    }

    @Provides
    @Singleton
    fun provideComicRepository(comicDataSource: ComicDataSource.Remote): ComicRepository {
        return ComicRepositoryImpl(comicDataSource)
    }

    @Provides
    @Singleton
    fun provideCreatorRepository(creatorDataSource: CreatorDataSource.Remote): CreatorRepository {
        return CreatorRepositoryImpl(creatorDataSource)
    }

    @Provides
    @Singleton
    fun provideEventRepository(eventDataSource: EventDataSource.Remote): EventRepository {
        return EventRepositoryImpl(eventDataSource)
    }

    @Provides
    @Singleton
    fun provideSeriesRepository(seriesDataSource: SeriesDataSource.Remote): SeriesRepository {
        return SeriesRepositoryImpl(seriesDataSource)
    }
}