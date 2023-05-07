package com.gun.data.repository

import com.gun.data.datasource.SeriesDataSource
import com.gun.data.mapper.toDomainModel
import com.gun.domain.model.Series
import com.gun.domain.repository.SeriesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SeriesRepositoryImpl(
    private val seriesDataSource: SeriesDataSource.Remote
) : SeriesRepository {
    override fun getSeries(page: Int, limit: Int): Flow<Result<List<Series>>> = flow {
        emit(seriesDataSource.getSeries(page, limit).map { it.toDomainModel() })
    }
}