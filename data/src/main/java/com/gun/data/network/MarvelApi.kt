package com.gun.data.network

import com.gun.data.entity.character.CharactersDto
import com.gun.data.entity.comic.ComicDto
import com.gun.data.entity.creator.CreatorDto
import com.gun.data.entity.event.EventDto
import com.gun.data.entity.series.SeriesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {
    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId: Int): CharactersDto

    @GET("/v1/public/characters")
    suspend fun getCharacterList(@Query("offset") offset: Int, @Query("limit") limit: Int): CharactersDto

    @GET("/v1/public/comics/{comicId}")
    suspend fun getComic(@Path("comicId") comicId: Int): ComicDto

    @GET("/v1/public/comics")
    suspend fun getComicList(@Query("offset") offset: Int, @Query("limit") limit: Int): ComicDto

    @GET("/v1/public/creators/{creatorId}")
    suspend fun getCreator(@Path("creatorId") creatorId: Int): CreatorDto

    @GET("/v1/public/creators")
    suspend fun getCreatorList(@Query("offset") offset: Int, @Query("limit") limit: Int): CreatorDto

    @GET("/v1/public/events/{eventId}")
    suspend fun getEvent(@Path("eventId") eventId: Int): EventDto

    @GET("/v1/public/events")
    suspend fun getEventList(@Query("offset") offset: Int, @Query("limit") limit: Int): EventDto

    @GET("/v1/public/series/{seriesId}")
    suspend fun getSeries(@Path("seriesId") seriesId: Int): SeriesDto

    @GET("/v1/public/series")
    suspend fun getSeriesList(@Query("offset") offset: Int, @Query("limit") limit: Int): SeriesDto
}