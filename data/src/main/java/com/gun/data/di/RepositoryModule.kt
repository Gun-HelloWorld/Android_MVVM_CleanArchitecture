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
    fun provideMarvelRepository(marvelRemoteDataSource: MarvelRemoteDataSource): MarvelRepository {
        return MarvelRepositoryImpl(marvelRemoteDataSource)
    }

}