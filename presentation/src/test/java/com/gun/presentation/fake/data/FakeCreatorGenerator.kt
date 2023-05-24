package com.gun.presentation.fake.data

import com.gun.domain.model.Creator
import com.gun.domain.model.SimpleInfo

object FakeCreatorGenerator {

    fun generate(id: Int): Creator {
        return Creator(
            id = id,
            fullName = "CreatorFullName$id",
            thumbnailPath = "CreatorThumbnailPath$id",
            thumbnailExtension = "CreatorThumbnailExtension$id",
            detailUrl = "CreatorDetailUrl$id",
            copyright = "CreatorCopyright$id",
            attributionText = "CreatorAttributionText$id",
            comicInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            seriesInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            storyInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            eventInfoList = listOf(SimpleInfo("Uri$id", "Name$id", ""))
        )
    }

    fun generate(offset: Int, limit: Int): List<Creator> {
        val fakeCreatorList = mutableListOf<Creator>()

        for (i in offset until limit) {
            fakeCreatorList.add(
                Creator(
                    id = i,
                    fullName = "CreatorFullName$i",
                    thumbnailPath = "CreatorThumbnailPath$i",
                    thumbnailExtension = "CreatorThumbnailExtension$i",
                    detailUrl = "CreatorDetailUrl$i",
                    copyright = "CreatorCopyright$i",
                    attributionText = "CreatorAttributionText$i",
                    comicInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    seriesInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    storyInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    eventInfoList = listOf(SimpleInfo("Uri$i", "Name$i", ""))
                )
            )
        }

        return fakeCreatorList
    }

}