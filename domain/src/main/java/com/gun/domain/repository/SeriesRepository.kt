package com.gun.domain.repository

import com.gun.domain.model.Series
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {
    fun getSeries(page: Int, limit: Int): Flow<Result<List<Series>>>
}