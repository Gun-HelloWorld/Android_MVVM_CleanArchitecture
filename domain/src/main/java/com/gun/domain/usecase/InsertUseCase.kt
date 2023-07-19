package com.gun.domain.usecase

import com.gun.domain.model.favorite.Favorite
import kotlinx.coroutines.flow.Flow

interface InsertUseCase {

    interface InsertFavoriteUseCase {
        operator fun invoke(favorite: Favorite): Flow<Result<Favorite>>
    }

}