package com.gun.presentation.fake.data

import com.gun.domain.model.Event
import com.gun.domain.model.SimpleInfo

object FakeDataGenerator {

    fun generate(id: Int): Event {
        return Event(
            id = id,
            title = "EventTitle$id",
            start = "EventStart$id",
            end = "EventEnd$id",
            description = "EventDescription$id",
            thumbnailPath = "EventThumbnailPath$id",
            thumbnailExtension = "EventThumbnailExtension$id",
            detailUrl = "EventDetailUrl$id",
            copyright = "EventCopyright$id",
            attributionText = "EventAttributionText$id",
            creatorInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "Role$id")),
            characterInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            storyInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            comicInfoList = listOf(SimpleInfo("Uri$id", "Name$id", "")),
            seriesInfoList = listOf(SimpleInfo("Uri$id", "Name$id", ""))
        )
    }

    fun generate(offset: Int, limit: Int): List<Event> {
        val fakeEventList = mutableListOf<Event>()

        for (i in offset until limit) {
            fakeEventList.add(
                Event(
                    id = i,
                    title = "EventTitle$i",
                    start = "EventStart$i",
                    end = "EventEnd$i",
                    description = "EventDescription$i",
                    thumbnailPath = "EventThumbnailPath$i",
                    thumbnailExtension = "EventThumbnailExtension$i",
                    detailUrl = "EventDetailUrl$i",
                    copyright = "EventCopyright$i",
                    attributionText = "EventAttributionText$i",
                    creatorInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "Role$i")),
                    characterInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    storyInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    comicInfoList = listOf(SimpleInfo("Uri$i", "Name$i", "")),
                    seriesInfoList = listOf(SimpleInfo("Uri$i", "Name$i", ""))
                )
            )
        }

        return fakeEventList
    }

}