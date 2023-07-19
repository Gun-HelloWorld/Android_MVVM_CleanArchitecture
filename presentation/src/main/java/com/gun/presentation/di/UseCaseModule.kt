package com.gun.presentation.di

import com.gun.domain.usecase.*
import com.gun.domain.usecase.detail.GetDetailDataUseCaseImpl
import com.gun.domain.usecase.favorite.DeleteFavoriteUseCaseImpl
import com.gun.domain.usecase.favorite.GetFavoriteListUseCaseImpl
import com.gun.domain.usecase.favorite.InsertFavoriteUseCaseImpl
import com.gun.domain.usecase.home.GetHomeDataUseCaseImpl
import com.gun.domain.usecase.search.GetSearchDataUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {

    @Binds
    @Singleton
    abstract fun bindGetHomeDataUseCase(
        getHomeDataUseCaseImpl: GetHomeDataUseCaseImpl
    ): GetUseCase.GetHomeDataUseCase

    @Binds
    @Singleton
    abstract fun bindGetDetailDataUseCase(
        getDetailDataUseCaseImpl: GetDetailDataUseCaseImpl
    ): GetUseCase.GetDetailDataUseCase

    @Binds
    @Singleton
    abstract fun bindGetSearchDataUseCase(
        getSearchDataUseCaseImpl: GetSearchDataUseCaseImpl
    ): GetUseCase.GetSearchDataUseCase

    @Binds
    @Singleton
    abstract fun bindGetFavoriteUseCase(
        getFavoriteListUseCaseImpl: GetFavoriteListUseCaseImpl
    ): GetUseCase.GetFavoriteUseCase

    @Binds
    @Singleton
    abstract fun bindInsertFavoriteUseCase(
        insertFavoriteUseCaseImplImpl: InsertFavoriteUseCaseImpl
    ): InsertUseCase.InsertFavoriteUseCase

    @Binds
    @Singleton
    abstract fun bindDeleteFavoriteUseCase(
        deleteFavoriteUseCaseImpl: DeleteFavoriteUseCaseImpl
    ): DeleteUseCase.DeleteFavoriteUseCase
}