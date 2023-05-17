package com.gun.domain.usecase

import com.gun.domain.model.Series
import kotlinx.coroutines.flow.Flow

interface SeriesUseCase {

    interface GetSeriesUseCase {
        operator fun invoke(page: Int, limit: Int): Flow<Result<List<Series>>>
    }

}