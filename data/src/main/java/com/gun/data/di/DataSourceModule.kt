package com.gun.data.di

import androidx.paging.PagingSource
import com.gun.data.database.MarvelDao
import com.gun.data.datasource.*
import com.gun.data.datasource.local.MarvelLocalDataSource
import com.gun.data.datasource.local.MarvelLocalDataSourceImpl
import com.gun.data.datasource.remote.MarvelRemoteDataSource
import com.gun.data.datasource.remote.MarvelRemoteDataSourceImpl
import com.gun.data.network.MarvelApi
import com.gun.data.datasource.remote.MarvelRemotePagingDataSourceImpl
import com.gun.domain.model.search.SearchResult
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

    @Provides
    @Singleton
    fun provideMarvelRemotePagingDataSource(marvelApi: MarvelApi) : PagingSource<Int, SearchResult> {
        return MarvelRemotePagingDataSourceImpl(marvelApi)
    }

    @Provides
    @Singleton
    fun provideMarvelLocalDataSource(marvelDao: MarvelDao): MarvelLocalDataSource {
        return MarvelLocalDataSourceImpl(marvelDao)
    }

}