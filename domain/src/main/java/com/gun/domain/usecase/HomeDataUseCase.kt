package com.gun.domain.usecase

import com.gun.domain.model.HomeList
import kotlinx.coroutines.flow.Flow

interface HomeDataUseCase {

    interface GetHomeDataUseCase {
        operator fun invoke(page: Int, limit: Int): Flow<Result<HomeList>>
    }

}