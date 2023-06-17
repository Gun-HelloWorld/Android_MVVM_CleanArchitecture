package com.gun.presentation.fake.data

import com.gun.data.entity.character.CharactersDto
import com.gun.data.entity.character.Data
import com.gun.data.entity.comic.ComicDto
import com.gun.data.entity.creator.CreatorDto
import com.gun.data.entity.event.EventDto
import com.gun.data.entity.series.SeriesDto

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
        val emptyData = com.gun.data.entity.series.Data(0, 0, 0, results = emptyList(), 0)

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
        val emptyData = com.gun.data.entity.comic.Data(0, 0, 0, results = emptyList(), 0)

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
        val emptyData = com.gun.data.entity.event.Data(0, 0, 0, results = emptyList(), 0)

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
        val emptyData = com.gun.data.entity.creator.Data(0, 0, 0, results = emptyList(), 0)

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