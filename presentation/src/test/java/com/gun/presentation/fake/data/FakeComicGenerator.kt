package com.gun.presentation.fake.data

import com.gun.domain.model.Comic
import com.gun.domain.model.SimpleInfo

object FakeComicGenerator {

    fun generate(id: Int): Comic {
        return Comic(
            id = id,
            title = "TestComicTitle$id",
            description = "TestComicDesc$id",
            format = "TestComicFormat$id",
            thumbnailPath = "TestComicThumbnailPath$id",
            thumbnailExtension = "TestComicThumbnailExtension$id",
            detailUrl = "TestComicDetailUrl$id",
            copyright = "TestComicCopyright$id",
            attributionText = "TestComicAttributionText$id",
            seriesInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            creatorInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "Role$id")),
            characterInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            storyInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            eventInfoList = listOf(SimpleInfo("Uri$id", "Name$id", ""))
        )
    }

    fun generate(offset: Int, limit: Int): List<Comic> {
        val fakeComicList = mutableListOf<Comic>()

        for (i in offset until limit) {
            fakeComicList.add(
                Comic(
                    id = i,
                    title = "ComicTitle$i",
                    description = "ComicDesc$i",
                    format = "ComicFormat$i",
                    thumbnailPath = "ComicThumbnailPath$i",
                    thumbnailExtension = "ComicThumbnailExtension$i",
                    detailUrl = "ComicDetailUrl$i",
                    copyright = "ComicCopyright$i",
                    attributionText = "ComicAttributionText$i",
                    seriesInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    creatorInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "Role$i")),
                    characterInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    storyInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    eventInfoList = listOf(SimpleInfo("Uri$i", "Name$i", ""))
                )
            )
        }

        return fakeComicList
    }

}