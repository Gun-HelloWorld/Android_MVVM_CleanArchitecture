package com.gun.domain.usecase.favorite

import com.gun.domain.common.ContentType
import com.gun.domain.model.favorite.Favorite
import com.gun.domain.repository.MarvelRepository
import com.gun.domain.usecase.GetUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.single
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration

@Singleton
class GetFavoriteListUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
) : GetUseCase.GetFavoriteUseCase {

    override fun invoke(contentType: ContentType?, shimmerDuration: Duration?): Flow<Result<List<Favorite>>> = flow {
        // For ShimmerEffect Showing
        val delayMs = shimmerDuration?.inWholeMilliseconds ?: 0
        if (delayMs > 0) delay(delayMs)

        val result = marvelRepository.getFavoriteList(contentType).single()

        if (result.isFailure) {
            emit(result)
            return@flow
        }

        val favoriteList = if (contentType == null) {
            result.getOrNull()
        } else {
            result.getOrNull()?.filter { it.contentType == contentType }
        }

        emit(Result.success(favoriteList ?: emptyList()))
    }
}