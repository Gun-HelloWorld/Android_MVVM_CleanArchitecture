package com.gun.presentation.fake.usecase

import com.gun.domain.model.home.HomeList
import com.gun.domain.usecase.GetUseCase
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeGetHomeDataUseCaseImpl : GetUseCase.GetHomeDataUseCase {
    private val fakeFlow = MutableSharedFlow<Result<HomeList>>()
    suspend fun emit(value: Result<HomeList>) = fakeFlow.emit(value)

    override operator fun invoke(offset: Int, limit: Int) = fakeFlow
}