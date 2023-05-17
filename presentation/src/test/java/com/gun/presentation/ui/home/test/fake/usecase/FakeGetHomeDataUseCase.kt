package com.gun.presentation.ui.home.test.fake.usecase

import com.gun.domain.model.*
import com.gun.domain.usecase.HomeDataUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeGetHomeDataUseCase : HomeDataUseCase.GetHomeDataUseCase {
    private var fakeCharacterList = mutableListOf<Character>()
    private var fakeComicList = mutableListOf<Comic>()
    private var fakeCreatorList = mutableListOf<Creator>()
    var fakeEventList = mutableListOf<Event>()
    private var fakeSeriesList = mutableListOf<Series>()

    override operator fun invoke(page: Int, limit: Int): Flow<Result<HomeList>> = flow {
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

    fun generateFakeCharacterList(page: Int, limit: Int): List<Character> {
        fakeCharacterList.clear()

        for (i in page until limit) {
            fakeCharacterList.add(
                Character(
                    id = i,
                    name = "TestCharacterName$i",
                    description = "TestCharacterDesc$i",
                    thumbnailPath = "TestCharacterThumbnailPath$i",
                    thumbnailExtension = "TestCharacterThumbnailExtension$i",
                    copyright = "TestCharacterCopyright$i",
                    attributionText = "TestCharacterAttributionText$i"
                )
            )
        }

        return fakeCharacterList
    }

    fun generateFakeComicList(page: Int, limit: Int): List<Comic> {
        fakeComicList.clear()

        for (i in page until limit) {
            fakeComicList.add(
                Comic(
                    id = i,
                    title = "TestComicTitle$i",
                    description = "TestComicDesc$i",
                    thumbnailPath = "TestComicThumbnailPath$i",
                    thumbnailExtension = "TestComicThumbnailExtension$i",
                    copyright = "TestComicCopyright$i",
                    attributionText = "TestComicAttributionText$i"
                )
            )
        }

        return fakeComicList
    }

    fun generateFakeCreatorList(page: Int, limit: Int): List<Creator> {
        fakeCreatorList.clear()

        for (i in page until limit) {
            fakeCreatorList.add(
                Creator(
                    id = i,
                    fullName = "TestCreatorFullName$i",
                    thumbnailPath = "TestCreatorThumbnailPath$i",
                    thumbnailExtension = "TestCreatorThumbnailExtension$i",
                )
            )
        }

        return fakeCreatorList
    }

    fun generateFakeEventList(page: Int, limit: Int): List<Event> {
        fakeEventList.clear()

        for (i in page until limit) {
            fakeEventList.add(
                Event(
                    id = i,
                    title = "TestEventTitle$i",
                    description = "TestEventDescription$i",
                    thumbnailPath = "TestEventThumbnailPath$i",
                    thumbnailExtension = "TestEventThumbnailExtension$i",
                    copyright = "TestEventCopyright$i",
                    attributionText = "TestEventAttributionText$i"
                )
            )
        }

        return fakeEventList
    }

    fun generateFakeSeriesList(page: Int, limit: Int): List<Series> {
        fakeSeriesList.clear()

        for (i in page until limit) {
            fakeSeriesList.add(
                Series(
                    id = i,
                    title = "TestSeriesTitle$i",
                    description = "TestSeriesDescription$i",
                    startYear = i,
                    endYear = i,
                    rating = "TestSeriesRating$i",
                    type = "TestSeriesType$i",
                    thumbnailPath = "TestSeriesThumbnailPath$i",
                    thumbnailExtension = "TestSeriesThumbnailExtension$i",
                    copyright = "TestSeriesCopyright$i",
                    attributionText = "TestSeriesAttributionText$i"
                )
            )
        }

        return fakeSeriesList
    }
}