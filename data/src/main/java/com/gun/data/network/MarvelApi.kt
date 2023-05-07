package com.gun.data.network

import com.gun.data.entity.character.CharactersDto
import com.gun.data.entity.comic.ComicDto
import com.gun.data.entity.creator.CreatorDto
import com.gun.data.entity.event.EventDto
import com.gun.data.entity.series.SeriesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/characters")
    suspend fun getCharacters(@Query("offset") page: Int, @Query("limit") limit: Int, ): CharactersDto

    @GET("/v1/public/comics")
    suspend fun getComics(@Query("offset") page: Int, @Query("limit") limit: Int): ComicDto

    @GET("/v1/public/creators")
    suspend fun getCreators(@Query("offset") page: Int, @Query("limit") limit: Int): CreatorDto

    @GET("/v1/public/events")
    suspend fun getEvents(@Query("offset") page: Int, @Query("limit") limit: Int): EventDto

    @GET("/v1/public/series")
    suspend fun getSeries(@Query("offset") page: Int, @Query("limit") limit: Int, ): SeriesDto
}