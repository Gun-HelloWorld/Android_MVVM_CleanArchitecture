package com.gun.domain.repository

import com.gun.domain.model.Series
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {
    fun getSeries(seriesId: Int): Flow<Result<List<Series>>>
    fun getSeriesList(offset: Int, limit: Int): Flow<Result<List<Series>>>
}