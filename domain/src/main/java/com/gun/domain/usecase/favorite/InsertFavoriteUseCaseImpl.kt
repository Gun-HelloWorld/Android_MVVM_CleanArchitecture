package com.gun.domain.usecase.favorite

import com.gun.domain.model.favorite.Favorite
import com.gun.domain.repository.MarvelRepository
import com.gun.domain.usecase.InsertUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InsertFavoriteUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
): InsertUseCase.InsertFavoriteUseCase {

    override fun invoke(favorite: Favorite): Flow<Result<Favorite>> {
        return marvelRepository.insertFavorite(favorite)
    }

}