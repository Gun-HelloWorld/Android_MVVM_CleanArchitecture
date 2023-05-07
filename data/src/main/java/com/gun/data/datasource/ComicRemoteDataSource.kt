package com.gun.data.datasource

import com.gun.data.entity.comic.ComicDto
import com.gun.data.network.MarvelApi

class ComicRemoteDataSource(private val marvelApi: MarvelApi) : ComicDataSource.Remote {
    override suspend fun getComics(page: Int, limit: Int): Result<ComicDto> = try {
        val result = marvelApi.getComics(page, limit)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }
}