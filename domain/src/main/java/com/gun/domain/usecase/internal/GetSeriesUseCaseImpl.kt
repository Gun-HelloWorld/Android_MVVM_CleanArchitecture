package com.gun.domain.usecase.internal

import com.gun.domain.model.Series
import com.gun.domain.repository.SeriesRepository
import com.gun.domain.usecase.SeriesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSeriesUseCaseImpl @Inject constructor(
    private val seriesRepository: SeriesRepository
) : SeriesUseCase.GetSeriesUseCase {

    override fun invoke(page: Int, limit: Int): Flow<Result<List<Series>>> = flow {
        if (limit == 0) {
            emit(Result.failure(IllegalArgumentException()))
            return@flow
        }

        seriesRepository.getSeries(page, limit)
    }

}