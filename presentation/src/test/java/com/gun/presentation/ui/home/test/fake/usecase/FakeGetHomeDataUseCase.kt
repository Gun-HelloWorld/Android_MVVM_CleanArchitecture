package com.gun.presentation.ui.home.test.fake.usecase

import com.gun.domain.model.Character
import com.gun.domain.model.Comic
import com.gun.domain.model.SimpleInfo
import com.gun.domain.model.Creator
import com.gun.domain.model.Event
import com.gun.domain.model.Series
import com.gun.domain.model.home.HomeList
import com.gun.domain.usecase.GetDataUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetHomeDataUseCase : GetDataUseCase.GetHomeDataUseCase {
    private var fakeCharacterList = mutableListOf<Character>()
    private var fakeComicList = mutableListOf<Comic>()
    private var fakeCreatorList = mutableListOf<Creator>()
    var fakeEventList = mutableListOf<Event>()
    private var fakeSeriesList = mutableListOf<Series>()

    override operator fun invoke(offset: Int, limit: Int): Flow<Result<HomeList>> = flow {
        if (limit == 0) {
            emit(Result.failure(IllegalArgumentException()))
            return@flow
        }

        emit(
            Result.success(
                HomeList(
                    fakeCharacterList,
                    fakeComicList,
                    fakeCreatorList,
                    fakeEventList,
                    fakeSeriesList
                )
            )
        )
    }

    fun generateFakeCharacterList(offset: Int, limit: Int): List<Character> {
        fakeCharacterList.clear()

        for (i in offset until limit) {
            fakeCharacterList.add(
                Character(
                    id = i,
                    name = "TestCharacterName$i",
                    description = "TestCharacterDesc$i",
                    thumbnailPath = "TestCharacterThumbnailPath$i",
                    thumbnailExtension = "TestCharacterThumbnailExtension$i",
                    detailUrl = "TestCharacterDetailUrl$i",
                    copyright = "TestCharacterCopyright$i",
                    attributionText = "TestCharacterAttributionText$i",
                    comicInfoList = listOf(
                        SimpleInfo(
                            "TestComicResourceUri$i",
                            "TestComicName$i",
                            ""
                        )
                    ),
                    seriesInfoList = listOf(
                        SimpleInfo(
                            "TestSeriesResourceUri$i",
                            "TestSeriesName$i",
                            ""
                        )
                    ),
                    storyInfoList = listOf(
                        SimpleInfo(
                            "TestStoryResourceUri$i",
                            "TestStoryName$i",
                            ""
                        )
                    ),
                    eventInfoList = listOf(
                        SimpleInfo(
                            "TestEventResourceUri$i",
                            "TestEventName$i",
                            ""
                        )
                    ),
                )
            )
        }

        return fakeCharacterList
    }

    fun generateFakeComicList(offset: Int, limit: Int): List<Comic> {
        fakeComicList.clear()

        for (i in offset until limit) {
            fakeComicList.add(
                Comic(
                    id = i,
                    title = "TestComicTitle$i",
                    description = "TestComicDesc$i",
                    format = "TestComicFormat$i",
                    thumbnailPath = "TestComicThumbnailPath$i",
                    thumbnailExtension = "TestComicThumbnailExtension$i",
                    detailUrl = "TestComicDetailUrl$i",
                    copyright = "TestComicCopyright$i",
                    attributionText = "TestComicAttributionText$i",
                    seriesInfoList = listOf(
                        SimpleInfo(
                            "TestSeriesResourceUri$i",
                            "TestSeriesName$i",
                            ""
                        )
                    ),
                    creatorInfoList = listOf(
                        SimpleInfo(
                            "TestCreatorResourceUri$i",
                            "TestCreatorName$i",
                            "TestCreatorRole$i"
                        )
                    ),
                    characterInfoList = listOf(
                        SimpleInfo(
                            "TestCharacterResourceUri$i",
                            "TestCharacterName$i",
                            ""
                        )
                    ),
                    storyInfoList = listOf(
                        SimpleInfo(
                            "TestStoryResourceUri$i",
                            "TestStoryName$i",
                            ""
                        )
                    ),
                    eventInfoList = listOf(
                        SimpleInfo(
                            "TestEventResourceUri$i",
                            "TestEventName$i",
                            ""
                        )
                    )
                )
            )
        }

        return fakeComicList
    }

    fun generateFakeCreatorList(offset: Int, limit: Int): List<Creator> {
        fakeCreatorList.clear()

        for (i in offset until limit) {
            fakeCreatorList.add(
                Creator(
                    id = i,
                    fullName = "TestCreatorFullName$i",
                    thumbnailPath = "TestCreatorThumbnailPath$i",
                    thumbnailExtension = "TestCreatorThumbnailExtension$i",
                    detailUrl = "TestCreatorDetailUrl$i",
                    copyright = "TestCreatorCopyright$i",
                    attributionText = "TestCreatorAttributionText$i",
                    comicInfoList = listOf(
                        SimpleInfo(
                            "TestComicResourceUri$i",
                            "TestComicName$i",
                            ""
                        )
                    ),
                    seriesInfoList = listOf(
                        SimpleInfo(
                            "TestSeriesResourceUri$i",
                            "TestSeriesName$i",
                            ""
                        )
                    ),
                    storyInfoList = listOf(
                        SimpleInfo(
                            "TestStoryResourceUri$i",
                            "TestStoryName$i",
                            ""
                        )
                    ),
                    eventInfoList = listOf(
                        SimpleInfo(
                            "TestEventResourceUri$i",
                            "TestEventName$i",
                            ""
                        )
                    )
                )
            )
        }

        return fakeCreatorList
    }

    fun generateFakeEventList(offset: Int, limit: Int): List<Event> {
        fakeEventList.clear()

        for (i in offset until limit) {
            fakeEventList.add(
                Event(
                    id = i,
                    title = "TestEventTitle$i",
                    start = "TestEventStart$i",
                    end = "TestEventEnd$i",
                    description = "TestEventDescription$i",
                    thumbnailPath = "TestEventThumbnailPath$i",
                    thumbnailExtension = "TestEventThumbnailExtension$i",
                    detailUrl = "TestEventDetailUrl$i",
                    copyright = "TestEventCopyright$i",
                    attributionText = "TestEventAttributionText$i",
                    creatorInfoList = listOf(
                        SimpleInfo(
                            "TestCreatorResourceUri$i",
                            "TestCreatorName$i",
                            "TestCreatorRole$i"
                        )
                    ),
                    characterInfoList = listOf(
                        SimpleInfo(
                            "TestCharacterResourceUri$i",
                            "TestCharacterName$i",
                            ""
                        )
                    ),
                    storyInfoList = listOf(
                        SimpleInfo(
                            "TestStoryResourceUri$i",
                            "TestStoryName$i",
                            ""
                        )
                    ),
                    comicInfoList = listOf(
                        SimpleInfo(
                            "TestComicResourceUri$i",
                            "TestComicName$i",
                            ""
                        )
                    ),
                    seriesInfoList = listOf(
                        SimpleInfo(
                            "TestSeriesResourceUri$i",
                            "TestSeriesName$i",
                            ""
                        )
                    )
                )
            )
        }

        return fakeEventList
    }

    fun generateFakeSeriesList(offset: Int, limit: Int): List<Series> {
        fakeSeriesList.clear()

        for (i in offset until limit) {
            fakeSeriesList.add(
                Series(
                    id = i,
                    title = "TestSeriesTitle$i",
                    description = "TestSeriesDescription$i",
                    startYear = i,
                    endYear = i,
                    rating = "TestSeriesRating$i",
                    thumbnailPath = "TestSeriesThumbnailPath$i",
                    thumbnailExtension = "TestSeriesThumbnailExtension$i",
                    detailUrl = "TestSeriesDetailUrl$i",
                    copyright = "TestSeriesCopyright$i",
                    attributionText = "TestSeriesAttributionText$i",
                    creatorInfoList = listOf(
                        SimpleInfo(
                            "TestCreatorResourceUri$i",
                            "TestCreatorName$i",
                            "TestCreatorRole$i"
                        )
                    ),
                    characterInfoList = listOf(
                        SimpleInfo(
                            "TestCharacterResourceUri$i",
                            "TestCharacterName$i",
                            ""
                        )
                    ),
                    storyInfoList = listOf(
                        SimpleInfo(
                            "TestStoryResourceUri$i",
                            "TestStoryName$i",
                            ""
                        )
                    ),
                    comicInfoList = listOf(
                        SimpleInfo(
                            "TestComicResourceUri$i",
                            "TestComicName$i",
                            ""
                        )
                    ),
                    eventInfoList = listOf(
                        SimpleInfo(
                            "TestEventResourceUri$i",
                            "TestEventName$i",
                            ""
                        )
                    )
                )
            )
        }

        return fakeSeriesList
    }
}