package com.gun.domain.usecase

import com.gun.domain.model.Series
import com.gun.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetSeriesUseCase @Inject constructor(
    private val seriesRepository: SeriesRepository
) {
    fun invoke(page: Int, limit: Int): Flow<Result<List<Series>>> = flow {
        if (limit == 0) {
            emit(Result.failure(IllegalArgumentException()))
            return@flow
        }

        seriesRepository.getSeries(page, limit)
    }
}