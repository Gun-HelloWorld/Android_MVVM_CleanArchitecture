package com.gun.domain.usecase.favorite

import com.gun.domain.common.ContentType
import com.gun.domain.model.favorite.Favorite
import com.gun.domain.repository.MarvelRepository
import com.gun.domain.usecase.GetUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetFavoriteListUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
) : GetUseCase.GetFavoriteUseCase {

    override fun invoke(contentType: ContentType?): Flow<Result<List<Favorite>>> {
        return marvelRepository.getFavoriteList(contentType)
    }
}