package com.gun.data.datasource

import com.gun.data.entity.series.SeriesDto
import com.gun.data.network.MarvelApi

class SeriesRemoteDataSource(private val marvelApi: MarvelApi) : SeriesDataSource.Remote {

    override suspend fun getSeries(seriesId: Int): Result<SeriesDto> = try {
        val result = marvelApi.getSeries(seriesId)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getSeriesList(offset: Int, limit: Int): Result<SeriesDto> = try {
        val result = marvelApi.getSeriesList(offset, limit)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

}