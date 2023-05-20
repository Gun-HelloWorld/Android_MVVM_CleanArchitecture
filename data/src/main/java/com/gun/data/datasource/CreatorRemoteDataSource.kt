package com.gun.data.datasource

import com.gun.data.entity.creator.CreatorDto
import com.gun.data.network.MarvelApi

class CreatorRemoteDataSource(private val marvelApi: MarvelApi) : CreatorDataSource.Remote {

    override suspend fun getCreator(creatorId: Int): Result<CreatorDto> = try {
        val result = marvelApi.getCreator(creatorId)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getCreatorList(offset: Int, limit: Int): Result<CreatorDto> = try {
        val result = marvelApi.getCreatorList(offset, limit)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

}