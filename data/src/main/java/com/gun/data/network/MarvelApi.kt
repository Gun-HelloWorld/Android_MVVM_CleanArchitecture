package com.gun.data.network

import com.gun.data.entity.response.remote.character.CharactersDto
import com.gun.data.entity.response.remote.comic.ComicDto
import com.gun.data.entity.response.remote.creator.CreatorDto
import com.gun.data.entity.response.remote.event.EventDto
import com.gun.data.entity.response.remote.series.SeriesDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    /****************************************** Character *****************************************/

    @GET("/v1/public/characters/{characterId}")
    suspend fun getCharacter(@Path("characterId") characterId: Int): CharactersDto

    @GET("/v1/public/characters")
    suspend fun getCharacterList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("nameStartsWith") nameStartsWith: String? = null,
    ): CharactersDto

    /******************************************** Comic *******************************************/

    @GET("/v1/public/comics/{comicId}")
    suspend fun getComic(@Path("comicId") comicId: Int): ComicDto

    @GET("/v1/public/comics")
    suspend fun getComicList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("titleStartsWith") titleStartsWith: String? = null,
    ): ComicDto

    /******************************************* Creator ******************************************/

    @GET("/v1/public/creators/{creatorId}")
    suspend fun getCreator(@Path("creatorId") creatorId: Int): CreatorDto

    @GET("/v1/public/creators")
    suspend fun getCreatorList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("firstNameStartsWith") firstNameStartsWith: String? = null
    ): CreatorDto

    /******************************************** Event *******************************************/

    @GET("/v1/public/events/{eventId}")
    suspend fun getEvent(@Path("eventId") eventId: Int): EventDto

    @GET("/v1/public/events")
    suspend fun getEventList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("nameStartsWith") nameStartsWith: String? = null
    ): EventDto

    /******************************************** Series ******************************************/

    @GET("/v1/public/series/{seriesId}")
    suspend fun getSeries(@Path("seriesId") seriesId: Int): SeriesDto

    @GET("/v1/public/series")
    suspend fun getSeriesList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("titleStartsWith") titleStartsWith: String? = null
    ): SeriesDto

}