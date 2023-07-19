package com.gun.presentation.fake.data

import com.gun.data.entity.response.remote.character.CharactersDto
import com.gun.data.entity.response.remote.character.Data
import com.gun.data.entity.response.remote.comic.ComicDto
import com.gun.data.entity.response.remote.creator.CreatorDto
import com.gun.data.entity.response.remote.event.EventDto
import com.gun.data.entity.response.remote.series.SeriesDto

object FakeDtoGenerator {

    fun generateEmptyCharactersDto(): CharactersDto {
        val emptyData = Data(0, 0, 0, results = emptyList(), 0)

        return CharactersDto(
            code = 0,
            status = "",
            copyright = "",
            attributionText = "",
            attributionHTML = "",
            etag = "",
            data = emptyData
        )
    }

    fun generateEmptySeriesDto(): SeriesDto {
        val emptyData =
            com.gun.data.entity.response.remote.series.Data(0, 0, 0, results = emptyList(), 0)

        return SeriesDto(
            code = 0,
            status = "",
            copyright = "",
            attributionText = "",
            attributionHTML = "",
            etag = "",
            data = emptyData
        )
    }

    fun generateEmptyComicDto(): ComicDto {
        val emptyData =
            com.gun.data.entity.response.remote.comic.Data(0, 0, 0, results = emptyList(), 0)

        return ComicDto(
            code = 0,
            status = "",
            copyright = "",
            attributionText = "",
            attributionHTML = "",
            etag = "",
            data = emptyData
        )
    }

    fun generateEmptyEventDto(): EventDto {
        val emptyData =
            com.gun.data.entity.response.remote.event.Data(0, 0, 0, results = emptyList(), 0)

        return EventDto(
            code = 0,
            status = "",
            copyright = "",
            attributionText = "",
            attributionHTML = "",
            etag = "",
            data = emptyData
        )
    }

    fun generateEmptyCreatorDto(): CreatorDto {
        val emptyData =
            com.gun.data.entity.response.remote.creator.Data(0, 0, 0, results = emptyList(), 0)

        return CreatorDto(
            code = 0,
            status = "",
            copyright = "",
            attributionText = "",
            attributionHTML = "",
            etag = "",
            data = emptyData
        )
    }

}