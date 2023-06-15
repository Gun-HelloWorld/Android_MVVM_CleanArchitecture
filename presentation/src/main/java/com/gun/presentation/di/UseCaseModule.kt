package com.gun.presentation.di

import com.gun.domain.usecase.*
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
    ): GetDataUseCase.GetHomeDataUseCase

    @Binds
    @Singleton
    abstract fun bindGetDetailDataUseCase(
        getDetailDataUseCaseImpl: GetDetailDataUseCaseImpl
    ): GetDataUseCase.GetDetailDataUseCase

    @Binds
    @Singleton
    abstract fun bindGetSearchDataUseCase(
        getSearchDataUseCaseImpl: GetSearchDataUseCaseImpl
    ): GetDataUseCase.GetSearchDataUseCase

}