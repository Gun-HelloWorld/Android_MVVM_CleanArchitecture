package com.gun.data.datasource

import com.gun.data.entity.creator.CreatorDto
import com.gun.data.network.MarvelApi

class CreatorRemoteDataSource(private val marvelApi: MarvelApi) : CreatorDataSource.Remote {
    override suspend fun getCreators(page: Int, limit: Int): Result<CreatorDto> = try {
        val result = marvelApi.getCreators(page, limit)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }
}