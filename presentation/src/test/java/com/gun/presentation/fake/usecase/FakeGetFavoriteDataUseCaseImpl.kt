package com.gun.presentation.fake.usecase

import com.gun.domain.common.ContentType
import com.gun.domain.model.favorite.Favorite
import com.gun.domain.usecase.GetUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlin.time.Duration

class FakeGetFavoriteDataUseCaseImpl : GetUseCase.GetFavoriteUseCase {
    private val fakeFlow = MutableSharedFlow<Result<List<Favorite>>>()
    suspend fun emit(value: Result<List<Favorite>>) = fakeFlow.emit(value)

    override fun invoke(contentType: ContentType?, shimmerDuration: Duration?) = fakeFlow
}