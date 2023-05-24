package com.gun.presentation.fake.usecase

import com.gun.domain.model.home.HomeList
import com.gun.domain.usecase.GetDataUseCase
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeGetHomeDataUseCaseImpl : GetDataUseCase.GetHomeDataUseCase {
    private val fakeFlow = MutableSharedFlow<Result<HomeList>>()
    suspend fun emit(value: Result<HomeList>) = fakeFlow.emit(value)

    override operator fun invoke(offset: Int, limit: Int) = fakeFlow
}