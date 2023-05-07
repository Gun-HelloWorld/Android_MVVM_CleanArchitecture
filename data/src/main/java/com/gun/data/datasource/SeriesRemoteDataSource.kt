package com.gun.data.datasource

import com.gun.data.entity.series.SeriesDto
import com.gun.data.network.MarvelApi

class SeriesRemoteDataSource(private val marvelApi: MarvelApi) : SeriesDataSource.Remote {
    override suspend fun getSeries(page: Int, limit: Int): Result<SeriesDto> = try {
        val result = marvelApi.getSeries(page, limit)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }
}