package com.gun.presentation.fake.usecase

import com.gun.domain.model.favorite.Favorite
import com.gun.domain.usecase.InsertUseCase
import kotlinx.coroutines.flow.MutableSharedFlow

class FakeInsertFavoriteUseCaseImpl : InsertUseCase.InsertFavoriteUseCase {
    private val fakeFlow = MutableSharedFlow<Result<Favorite>>()
    suspend fun emit(value: Result<Favorite>) = fakeFlow.emit(value)

    override fun invoke(favorite: Favorite) = fakeFlow
}