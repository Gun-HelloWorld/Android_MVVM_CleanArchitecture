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
    fun provideMarvelRemoteDataSource(marvelApi: MarvelApi): MarvelRemoteDataSource {
        return MarvelRemoteDataSourceImpl(marvelApi)
    }

}