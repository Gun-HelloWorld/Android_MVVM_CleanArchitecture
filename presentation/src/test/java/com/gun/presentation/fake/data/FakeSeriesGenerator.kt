package com.gun.presentation.fake.data

import com.gun.domain.model.Series
import com.gun.domain.model.SimpleInfo

object FakeSeriesGenerator {

    fun generate(id: Int): Series {
        return Series(
            id = id,
            title = "SeriesTitle$id",
            description = "SeriesDescription$id",
            startYear = id,
            endYear = id,
            rating = "SeriesRating$id",
            thumbnailPath = "SeriesThumbnailPath$id",
            thumbnailExtension = "SeriesThumbnailExtension$id",
            detailUrl = "SeriesDetailUrl$id",
            copyright = "SeriesCopyright$id",
            attributionText = "SeriesAttributionText$id",
            creatorInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "Role$id")),
            characterInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            storyInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            comicInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            eventInfoList = listOf(SimpleInfo("Uri$id", "Name$id", ""))
        )
    }

    fun generate(offset: Int, limit: Int): List<Series> {
        val fakeSeriesList = mutableListOf<Series>()

        for (i in offset until limit) {
            fakeSeriesList.add(
                Series(
                    id = i,
                    title = "SeriesTitle$i",
                    description = "SeriesDescription$i",
                    startYear = i,
                    endYear = i,
                    rating = "SeriesRating$i",
                    thumbnailPath = "SeriesThumbnailPath$i",
                    thumbnailExtension = "SeriesThumbnailExtension$i",
                    detailUrl = "SeriesDetailUrl$i",
                    copyright = "SeriesCopyright$i",
                    attributionText = "SeriesAttributionText$i",
                    creatorInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "Role$i")),
                    characterInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    storyInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    comicInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    eventInfoList = listOf(SimpleInfo("Uri$i", "Name$i", ""))
                )
            )
        }

        return fakeSeriesList
    }

}