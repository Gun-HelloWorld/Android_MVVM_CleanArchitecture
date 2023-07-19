package com.gun.domain.usecase.favorite

import com.gun.domain.model.favorite.Favorite
import com.gun.domain.repository.MarvelRepository
import com.gun.domain.usecase.DeleteUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteFavoriteUseCaseImpl @Inject constructor(
    private val marvelRepository: MarvelRepository
): DeleteUseCase.DeleteFavoriteUseCase {

    override fun invoke(favorite: Favorite): Flow<Result<Favorite>> {
        return marvelRepository.deleteFavorite(favorite)
    }

}