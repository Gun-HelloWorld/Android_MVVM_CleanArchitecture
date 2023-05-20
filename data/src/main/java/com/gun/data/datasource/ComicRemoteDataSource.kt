package com.gun.data.datasource

import com.gun.data.entity.comic.ComicDto
import com.gun.data.network.MarvelApi

class ComicRemoteDataSource(private val marvelApi: MarvelApi) : ComicDataSource.Remote {

    override suspend fun getComic(comicId: Int): Result<ComicDto> = try {
        val result = marvelApi.getComic(comicId)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

    override suspend fun getComicList(offset: Int, limit: Int): Result<ComicDto> = try {
        val result = marvelApi.getComicList(offset, limit)
        Result.success(result)
    } catch (e: Exception) {
        Result.failure(e)
    }

}