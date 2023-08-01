package com.gun.presentation.fake.usecase

import com.gun.domain.model.favorite.Favorite
import com.gun.domain.usecase.DeleteUseCase
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeDeleteFavoriteUseCaseImpl : DeleteUseCase.DeleteFavoriteUseCase {
    private val fakeFlow = MutableSharedFlow<Result<Favorite>>()
    suspend fun emit(value: Result<Favorite>) = fakeFlow.emit(value)

    override fun invoke(favorite: Favorite) = fakeFlow
}