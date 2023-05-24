package com.gun.presentation.fake.data

import com.gun.domain.model.Event
import com.gun.domain.model.SimpleInfo

object FakeEventGenerator {

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

    fun generateInvalidThumbnailList(): List<Event> {
        // 썸네일 경로 "image_not_available" 문구 포함 될 시 유효하지 않은 썸네일
        val imageNotAvailableCase = Event(0, "", "", "", "", thumbnailPath = "image_not_available", thumbnailExtension = "정상확장자","", "", "", emptyList(), emptyList(), emptyList(), emptyList(), emptyList())

        // 썸네일 경로 비어있을 시 유효하지 않은 썸네일
        val thumbnailPathEmptyCase = Event(1, "", "", "", "", thumbnailPath = "", thumbnailExtension = "정상확장자", "", "", "", emptyList(), emptyList(), emptyList(), emptyList(), emptyList())

        // 썸네일 확장자 비어있을 시 유효하지 않은 썸네일
        val extensionEmptyCase = Event(2, "", "", "", "", thumbnailPath = "https://정상이미지경로.com/", thumbnailExtension = "", "", "", "", emptyList(), emptyList(), emptyList(), emptyList(), emptyList())

        return listOf(imageNotAvailableCase, thumbnailPathEmptyCase, extensionEmptyCase)
    }
}