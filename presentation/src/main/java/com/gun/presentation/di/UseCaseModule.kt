package com.gun.presentation.di

import com.gun.domain.usecase.HomeDataUseCase
import com.gun.domain.usecase.internal.GetHomeDataUseCaseImpl
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
    ): HomeDataUseCase.GetHomeDataUseCase
}