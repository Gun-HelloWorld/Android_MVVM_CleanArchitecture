package com.gun.presentation.fake.data

import com.gun.domain.model.home.HomeList

object FakeHomeListGenerator {

    fun generate(offset: Int, limit: Int) = HomeList(
        characterList = FakeCharacterGenerator.generate(offset, limit),
        comicList = FakeComicGenerator.generate(offset, limit),
        creatorList = FakeCreatorGenerator.generate(offset, limit),
        eventList = FakeEventGenerator.generate(offset, limit),
        seriesList = FakeSeriesGenerator.generate(offset, limit
        )
    )

}